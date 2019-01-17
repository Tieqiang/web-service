package com.dchealth.webservice.service;

import com.dchealth.webservice.util.Utils;
import com.dchealth.webservice.vo.response.PersonInfo;
import org.springframework.stereotype.Service;


@Service("RHC_E0016")
public class CardRegistForNewBornService extends BaseService {
    @Override
    public Object execRequest(String message) throws Exception {
        PersonInfo personInfo = new PersonInfo();
        PersonInfo instance = Utils.getInstance(personInfo);
        return instance;
    }
}
