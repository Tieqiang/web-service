package com.dchealth.webservice.service;


import com.dchealth.webservice.vo.BaseResponse;
import org.springframework.stereotype.Service;

@Service("RHC_E0009")
public class CardUseRecordService extends BaseService {

    @Override
    public Object execRequest(String message) throws Exception {
        return new BaseResponse();
    }
}