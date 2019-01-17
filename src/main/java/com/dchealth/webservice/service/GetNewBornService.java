package com.dchealth.webservice.service;

import com.dchealth.webservice.vo.response.NewBornResponse;
import com.dchealth.webservice.vo.response.PersonInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RHC_E0015")
public class GetNewBornService extends BaseService {
    @Override
    public Object execRequest(String message) throws Exception {
        NewBornResponse newBornResponse = new NewBornResponse();


        List<Object> personInfos = new ArrayList<>();
        for(int i =0 ;i<3;i++){
            PersonInfo personInfo = new PersonInfo();
            personInfo.setName("新生儿"+i);
            personInfos.add(personInfo);
        }
        newBornResponse.setEntities(personInfos);
        return newBornResponse;
    }
}
