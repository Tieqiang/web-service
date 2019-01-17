package com.dchealth.webservice.service;


import com.dchealth.webservice.vo.response.CardFamilySearchResponse;
import com.dchealth.webservice.vo.response.PersonInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RHC_E0010")
public class CardFamilySearchService extends BaseService {

    @Override
    public Object execRequest(String message) throws Exception {
        CardFamilySearchResponse cardFamillySearchResponse = new CardFamilySearchResponse();
        cardFamillySearchResponse.setTotal("1000");
        List<Object> objects = new ArrayList<>();

        for(int i = 0 ;i<10;i++){
            PersonInfo personInfo = new PersonInfo();
            personInfo.setName("zhangan"+i);
            objects.add(personInfo);
        }
        cardFamillySearchResponse.setEntities(objects);
        return cardFamillySearchResponse;
    }
}