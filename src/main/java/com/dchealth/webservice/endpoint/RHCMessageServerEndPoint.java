package com.dchealth.webservice.endpoint;


import com.cdxt.ehc.webservice.RHCMessageServerRequest;
import com.cdxt.ehc.webservice.RHCMessageServerResponse;
import com.dchealth.webservice.service.BaseService;
import com.dchealth.webservice.vo.*;
import com.dchealth.webservice.vo.response.EHCCardInfo;
import com.dchealth.webservice.vo.response.NewBornResponse;
import com.dchealth.webservice.vo.response.PersonInfo;
import com.dchealth.webservice.vo.response.TempCardApplyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


@Endpoint
public class RHCMessageServerEndPoint implements ApplicationContextAware {


    private ApplicationContext context ;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static final String NAMESPACE_URI = "http://webservice.ehc.cdxt.com/";

    @Autowired
    private ObjectMapper objectMapper;

    //配置对外接口
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RHCMessageServer")
    @ResponsePayload
    public RHCMessageServerResponse RHCMessageServer(@RequestPayload RHCMessageServerRequest rhcMessageServerRequest) throws Exception {

        JAXBContext jc = JAXBContext.newInstance(BaseResponse.class, EHCCardInfo.class, NewBornResponse.class, CardRegistMessage.class, PersonInfo.class, TempCardApplyResponse.class, ActionObject.class);
        Marshaller marshaller = jc.createMarshaller();
        Unmarshaller unmarshaller = jc.createUnmarshaller();


        ObjectMapper xmlMapper = new XmlMapper();
        String action = rhcMessageServerRequest.getArg0();
        String message = rhcMessageServerRequest.getArg1();
        ActionObject actionObject =(ActionObject) unmarshaller.unmarshal(new StringReader(action));
        System.out.println(objectMapper.writeValueAsString(actionObject));

        String bussinessCode = actionObject.getBussinessCode();
        BaseService baseService = (BaseService) context.getBean(bussinessCode);
        Object responseMessage = baseService.execRequest(message);
        ResponseInterface baseResponse = null ;
        if(responseMessage instanceof ResponseInterface){
            baseResponse = (ResponseInterface) responseMessage;
        }else{
            baseResponse =new BaseResponse();
            List<Object> list = new ArrayList<>();
            list.add(responseMessage);
            baseResponse.setEntities(list);
        }
        StringWriter writer = new StringWriter();
        marshaller.marshal(baseResponse,writer);

        RHCMessageServerResponse response = new RHCMessageServerResponse();
        response.setReturn(writer.toString());
        return response;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
