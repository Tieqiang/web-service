package com.dchealth.webservice.service;

import com.dchealth.webservice.vo.ActionObject;
import com.dchealth.webservice.vo.BaseResponse;
import com.dchealth.webservice.vo.CardRegistMessage;
import com.dchealth.webservice.vo.PersonInfo;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Service(value = "RHC_E0001")
public class CardRegistService extends BaseService {


    @Override
    public Object execRequest(String message) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(BaseResponse.class, CardRegistMessage.class, PersonInfo.class, ActionObject.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        JAXBElement<CardRegistMessage> obj = (JAXBElement<CardRegistMessage> ) unmarshaller.unmarshal(new StreamSource(new StringReader(message)) ,CardRegistMessage.class);
        CardRegistMessage cardRegistMessage = obj.getValue();
        PersonInfo personInfo= new PersonInfo();
        personInfo.setAddress("address");
        return personInfo;
    }
}
