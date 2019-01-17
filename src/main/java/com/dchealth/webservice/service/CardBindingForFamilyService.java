package com.dchealth.webservice.service;


import com.dchealth.webservice.vo.ActionObject;
import com.dchealth.webservice.vo.BaseResponse;
import com.dchealth.webservice.vo.CardRegistMessage;
import com.dchealth.webservice.vo.request.CardBindingForFamilyMessage;
import com.dchealth.webservice.vo.response.PersonInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service(value = "RHC_E0007")
public class CardBindingForFamilyService extends BaseService {

    @Override
    public Object execRequest(String message) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(BaseResponse.class, CardRegistMessage.class, PersonInfo.class,CardBindingForFamilyMessage.class, ActionObject.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        JAXBElement<CardBindingForFamilyMessage> obj = (JAXBElement<CardBindingForFamilyMessage> ) unmarshaller.unmarshal(new StreamSource(new StringReader(message)) ,CardBindingForFamilyMessage.class);
        CardBindingForFamilyMessage cardBindingForFamillyMessage = obj.getValue();
        PersonInfo personInfoMessage = new PersonInfo();

        BeanUtils.copyProperties(cardBindingForFamillyMessage, personInfoMessage);
        BaseResponse baseResponse = new BaseResponse();
        List<Object> list = new ArrayList<>();
        list.add(personInfoMessage);
        baseResponse.setEntities(list);
        return baseResponse;
    }
}