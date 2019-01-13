package com.dchealth.webservice.service;

import com.dchealth.webservice.vo.CardRegistMessage;
import com.dchealth.webservice.vo.PersonInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service(value = "RHC_E0001")
public class CardRegistService extends BaseService {


    @Override
    public Object execRequest(String message) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        CardRegistMessage cardRegistMessage = objectMapper.readValue(message, CardRegistMessage.class);
        PersonInfo personInfo= new PersonInfo();
        personInfo.setAddress("address");
        return personInfo;
    }
}
