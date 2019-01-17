package com.dchealth.webservice.service;


import com.dchealth.webservice.util.Utils;
import com.dchealth.webservice.vo.response.EHCCardInfo;
import org.springframework.stereotype.Service;

@Service("RHC_E0005")
public class QcodeSearchService extends BaseService{

    @Override
    public Object execRequest(String message) throws Exception {
        EHCCardInfo instance = Utils.getInstance(new EHCCardInfo());
        return instance;
    }
}