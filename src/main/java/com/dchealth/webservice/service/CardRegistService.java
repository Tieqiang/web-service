package com.dchealth.webservice.service;

import com.dchealth.webservice.vo.ActionObject;
import com.dchealth.webservice.vo.BaseResponse;
import com.dchealth.webservice.vo.CardRegistMessage;
import com.dchealth.webservice.vo.EntitiesResponse;
import com.dchealth.webservice.vo.response.PersonInfo;
import com.dchealth.webservice.vo.response.TempCardApplyResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service(value = "RHC_E0001")
public class CardRegistService extends BaseService {


    @Override
    public Object execRequest(String message) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(BaseResponse.class, CardRegistMessage.class, PersonInfo.class, ActionObject.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        JAXBElement<CardRegistMessage> obj = (JAXBElement<CardRegistMessage> ) unmarshaller.unmarshal(new StreamSource(new StringReader(message)) ,CardRegistMessage.class);
        CardRegistMessage cardRegistMessage = obj.getValue();
        PersonInfo personInfoMessage = new PersonInfo();
        personInfoMessage.setAddress("新的地址");
        TempCardApplyResponse tempCardApplyResponse = new TempCardApplyResponse();
        tempCardApplyResponse.setQr_code("123123123");
        PersonInfo p = new PersonInfo();
        BeanUtils.copyProperties(cardRegistMessage, p);
        EntitiesResponse baseResponse = new EntitiesResponse();
        List<Object> list = new ArrayList<>();
        list.add(p);
        baseResponse.setEntities(list);
        return baseResponse;
//        CardFamilySearchResponse cardFamillySearchResponse = new CardFamilySearchResponse();
//        cardFamillySearchResponse.setTotal(1000);
//        List<Object> objects = new ArrayList<>();
//
//        for(int i = 0 ;i<10;i++){
//            PersonInfo personInfo = new PersonInfo();
//            personInfo.setName("zhangan"+i);
//            objects.add(personInfo);
//        }
//        cardFamillySearchResponse.setEntities(objects);
//        return cardFamillySearchResponse;
    }
}
