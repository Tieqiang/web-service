package com.dchealth.webservice.service;


import com.dchealth.webservice.vo.response.PersonInfo;
import org.springframework.stereotype.Service;


@Service("RHC_E0006")
public class CardRegistConfirmService extends BaseService {

    @Override
    public Object execRequest(String message) throws Exception {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("张三测试");
        return personInfo;
    }
}