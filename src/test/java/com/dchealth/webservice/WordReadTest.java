package com.dchealth.webservice;


import com.dchealth.webservice.utils.ClassScaner;
import com.dchealth.webservice.utils.StringUtil;
import com.dchealth.webservice.vo.BussinessCode;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class WordReadTest {


    public List<ObjectSupper> readWordTest() throws IOException, OpenXML4JException, IllegalAccessException {

        HashMap<String, String> nameKeys = this.getNameKeys();

//        POIXMLDocument
        FileInputStream fileInputStream = new FileInputStream(new File(this.getClass().getResource("/1.docx").getPath()));
        XWPFDocument document = new XWPFDocument(fileInputStream);

        System.out.println("----------------------------所有文档结束----------------------------------");

        List<IBodyElement> bodyElements = document.getBodyElements();
        int inteface = -1;
        int tableCount = -1;
        List<ObjectSupper> objectSuppers = new ArrayList<>();
        ObjectSupper objectSupper = null;
        for (IBodyElement element : bodyElements) {
            BodyElementType elementType = element.getElementType();

            POIXMLDocumentPart part = element.getPart();
            if ("PARAGRAPH".equals(elementType.name())) {
                XWPFParagraph paragraph = (XWPFParagraph) element;
                String text1 = paragraph.getText();
                if ("接口描述".equals(text1)) {
                    inteface = 0;
                    tableCount = 0;
                    objectSupper = new ObjectSupper();
                } else {
                    if (inteface == 0 && tableCount == 1) {
                        if (text1.contains("businessCode")) {
                            String substring = text1.substring(text1.length() - 9);
                            System.out.println(substring);
                            objectSupper.setName(nameKeys.get(substring));
                            objectSupper.setCode(substring);
                        }
                    }
                }
            }

            if ("TABLE".equals(elementType.name())) {
                if (inteface == 0) {
                    XWPFTable table = (XWPFTable) element;
                    switch (tableCount) {
                        case 0:
                            //接口描述
                            XWPFTableRow row = table.getRow(2);
                            XWPFTableCell cell = row.getCell(1);
                            String text = cell.getText();
                            objectSupper.setOperationDescription(text);
                            tableCount++;
                            break;
                        case 1:
                            //入参

                            List<XWPFTableRow> rows = table.getRows();
                            int size = rows.size();
                            for (int i = 1; i < size; i++) {
                                XWPFTableRow tableRow = rows.get(i);
                                if (i == size - 1) {
                                    String text1 = tableRow.getCell(0).getText();
                                    objectSupper.setRequestDemo(text1);
                                } else {
                                    if (tableRow.getTableCells().size() > 1) {
                                        FieldBean bean = new FieldBean();
                                        bean.setCnName(tableRow.getCell(1).getText());
                                        bean.setName(tableRow.getCell(2).getText());
                                        bean.setfType(tableRow.getCell(3).getText());
                                        bean.setDesc(tableRow.getCell(6).getText());
                                        objectSupper.getMessageBean().add(bean);
                                        System.out.println("请求参数：" + objectSupper.getMessageBean().size());
                                    }
                                }
                            }

                            tableCount++;
                            break;
                        case 2:
                            //出参
                            List<XWPFTableRow> rowList = table.getRows();
                            int count = rowList.size();
                            System.out.println(count);
                            for (int i = 1; i < count; i++) {
                                XWPFTableRow tableRow = rowList.get(i);
                                if (i == count - 1) {
                                    String text1 = tableRow.getCell(0).getText();
                                    objectSupper.setResponseDemo(text1);
                                    System.out.println(text1);
                                } else {
                                    if (tableRow.getTableCells().size() > 1) {
                                        FieldBean bean = new FieldBean();
                                        bean.setCnName(tableRow.getCell(1).getText());
                                        bean.setName(tableRow.getCell(2).getText());
                                        bean.setfType(tableRow.getCell(3).getText());
                                        bean.setDesc(tableRow.getCell(5).getText());
                                        objectSupper.getResponseBean().add(bean);
                                        System.out.println("响应出参：" + objectSupper.getResponseBean().size());
                                    }

                                }
                            }

                            objectSuppers.add(objectSupper);
                            tableCount++;
                    }
                }

            }

        }

        System.out.println(objectSuppers);
        fileInputStream.close();
        document.close();
        return objectSuppers;
    }


    private HashMap<String, String> getNameKeys() throws IllegalAccessException {
        HashMap<String, String> maps = new HashMap<>();
        Field[] fields = BussinessCode.class.getDeclaredFields();
        BussinessCode code = new BussinessCode();
        for (Field field : fields) {
            String name = field.getName();
            Object o = field.get(code);
            maps.put(o.toString(), name);
        }
        return maps;
    }


    @Test
    public void createClass() throws OpenXML4JException, IllegalAccessException, IOException, DocumentException {


//        List<ObjectSupper> objectSuppers = this.readWordTest();
//        objectSuppers = rebuildObjectSuppers(objectSuppers);
//        objectSuppers = nextBuildObjectSuppers(objectSuppers);//去重
//        createMessage(objectSuppers);
//        createPbMessage(objectSuppers);
        createPbResponse();
//        createResponse(objectSuppers);
//        createService(objectSuppers);

    }

    private void createService(List<ObjectSupper> objectSuppers) {

    }

    private void createResponse(List<ObjectSupper> objectSuppers) throws IOException {
        for (ObjectSupper objectSupper : objectSuppers) {
            String responseName = objectSupper.getResponseName();
            System.out.println(responseName);
            String className = this.getClassName(objectSupper) + "Response";
            String str = "import javax.xml.bind.annotation.XmlRootElement;\r\n" +
                    "\r\n" +
                    "@XmlRootElement(name = \"${response}\")\r\n";
            String response = "";


            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("public class " + className + "{\r\n");
            List<FieldBean> responseBean = objectSupper.getResponseBean();
            for (FieldBean bean : responseBean) {
                if (responseName != null) {
                    response = responseName;
                    if ("result".equals(bean.getName()) || "desc".equals(bean.getName()) || "resultCode".equals(bean.getName())) {
                        continue;
                    }
                } else {
                    response = "response";
                }
                stringBuilder.append("\tprivate " + bean.getfType() + " " + bean.getName() + ";//" + bean.getCnName() + "|" + bean.getDesc() + "\r\n");

            }
            stringBuilder.append("}");
            str = str.replace("${response}", response);
            str += stringBuilder.toString();

            File file = new File(this.getClass().getResource("").getPath() + "/" + className + ".java");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();

        }
    }

    private void createMessage(List<ObjectSupper> objectSuppers) throws IOException {

        File rFile = new File(this.getClass().getResource("/PbRequest.template").getPath());
        FileReader fileReader = new FileReader(rFile);
        char[] buffer = new char[2000];
        fileReader.read(buffer);
        String souceTemplate = new String(buffer);
        fileReader.close();

        for (ObjectSupper objectSupper : objectSuppers) {

            String className = this.getClassName(objectSupper) + "Message";
            String template = souceTemplate;
            template = template.replace("${className}", className).trim();
            List<FieldBean> messageBean = objectSupper.getMessageBean();
            for (FieldBean bean : messageBean) {
                String fileStr = "\tprivate " + bean.getfType() + " " + bean.getName() + " ;//" + bean.getCnName() + "|" + bean.getDesc();
                template += fileStr + "\r\n";
            }
            template += "\tpublic " + className + "(){\r\n\t}\r\n";
            template += "\t";
            template += "}";
            File file = new File(className + ".java");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(template);
            fileWriter.flush();
            fileWriter.close();
        }
    }

    private void createPbMessage(List<ObjectSupper> objectSuppers) throws IOException {

        File rFile = new File(this.getClass().getResource("/PbRequest.template").getPath());
        FileReader fileReader = new FileReader(rFile);
        char[] buffer = new char[2000];
        fileReader.read(buffer);
        String souceTemplate = new String(buffer);
        fileReader.close();

        for (ObjectSupper objectSupper : objectSuppers) {

            String className = this.getPbClassName(objectSupper) + "_message";
            String template = souceTemplate;
            template = template.replace("${className}", className).trim();
            List<FieldBean> messageBean = objectSupper.getMessageBean();
            StringBuilder sbVarible = new StringBuilder();
            StringBuilder sbToJson = new StringBuilder();
            StringBuilder sbParseJson = new StringBuilder();

            for (FieldBean bean : messageBean) {
                sbVarible.append(bean.getfType()).append(" msg_").append(bean.getName()).append("\r\n");
                sbToJson.append("json.setattribute( \"").append(bean.getName()).append("\",").append("msg_").append(bean.getName()).append(")").append("\r\n");
                sbParseJson.append("msg_").append(bean.getName()).append("= json.getattribute( \"").append(bean.getName()).append("\")").append("\r\n");
            }
            template = template.replace("${variables}", sbVarible.toString());
            template = template.replace("${toJson}", sbToJson.toString());
            template = template.replace("${parseJson}", sbParseJson.toString());
            File file = new File(this.getClass().getResource("").getPath() + "/" + className + ".sru");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(template);
            fileWriter.flush();
            fileWriter.close();
        }
    }

    private String readTemplate(String path) throws IOException {
        File rFile = new File(path);
        FileReader fileReader = new FileReader(rFile);
        char[] buffer = new char[2000];
        fileReader.read(buffer);
        String souceTemplate = new String(buffer);
        return souceTemplate;
    }
    private void createPbResponse() throws IOException {

        String souceTemplate = this.readTemplate(this.getClass().getResource("/PbResponse.template").getPath());
        String parseJsonEntitiesTemplate = this.readTemplate(this.getClass().getResource("/PbResponseParseJsonEntities.template").getPath());
        String toJsonEntitiesTemplate = this.readTemplate(this.getClass().getResource("/PbResponseToJsonEntities.template").getPath());
        Set<Class> set = ClassScaner.scan("com.dchealth.webservice.vo.response", null);
        String entityName = "person_info";
        for (Class clazz : set) {
            String template = souceTemplate;
            StringBuilder sbVarible = new StringBuilder();
            StringBuilder sbToJson = new StringBuilder();
            StringBuilder sbParseJson = new StringBuilder();
            String className = StringUtil.underscoreName(clazz.getSimpleName());
            parseJsonEntitiesTemplate = parseJsonEntitiesTemplate.replace("${entityName}", entityName);
            toJsonEntitiesTemplate = toJsonEntitiesTemplate.replace("${entityName}", entityName);
            for (PropertyDescriptor d : BeanUtils.getPropertyDescriptors(clazz)) {
                if (d.getName().equalsIgnoreCase("class")) {
                    continue;
                }
                if (d.getName().equalsIgnoreCase("entities")) {
                    sbVarible.append(entityName).append(" ").append("res_entities[]").append("\r\n");
                    sbParseJson.append(parseJsonEntitiesTemplate).append("\r\n");
                    sbToJson.append(toJsonEntitiesTemplate).append("\r\n");
                } else {
                    sbVarible.append(d.getPropertyType().getSimpleName()).append(" ").append("res_").append(d.getName()).append("\r\n");
                    sbToJson.append("json.setattribute( \"").append(d.getName()).append("\",").append("res_").append(d.getName()).append(")").append("\r\n");
                    sbParseJson.append("res_").append(d.getName()).append("= json.getattribute( \"").append(d.getName()).append("\")").append("\r\n");
                }

            }
            template = template.replace("${className}", className).trim();
            template = template.replace("${variables}", sbVarible.toString());
            template = template.replace("${toJson}", sbToJson.toString());
            template = template.replace("${parseJson}", sbParseJson.toString());
            File file = new File(this.getClass().getResource("").getPath() + "/" + className + ".sru");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(template);
            fileWriter.flush();
            fileWriter.close();
        }
    }

    private String getClassName(ObjectSupper objectSupper) {
        String name = objectSupper.getName();
        String[] messages = name.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String msg : messages) {
            msg = msg.toLowerCase();
            msg = msg.substring(0, 1).toUpperCase() + msg.substring(1);
            stringBuilder.append(msg);
        }
        return stringBuilder.toString();
    }

    private String getPbClassName(ObjectSupper objectSupper) {
        String name = objectSupper.getName();
        return name.toLowerCase();
    }

    /**
     * 字段属性去重
     *
     * @param objectSuppers
     * @return
     */
    private List<ObjectSupper> nextBuildObjectSuppers(List<ObjectSupper> objectSuppers) {
        return null;
    }

    private List<ObjectSupper> rebuildObjectSuppers(List<ObjectSupper> objectSuppers) throws DocumentException {

        for (ObjectSupper objectSupper : objectSuppers) {
            String responseDemo = objectSupper.getResponseDemo();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new StringReader(responseDemo));
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element next = (Element) iterator.next();
                List elements = next.elements();
                if (elements.size() > 0) {
                    objectSupper.setResponseName(next.getName());
                }
            }
        }
        return objectSuppers;
    }


}
