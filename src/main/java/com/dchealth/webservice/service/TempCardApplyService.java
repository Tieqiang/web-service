package com.dchealth.webservice.service;


import com.dchealth.webservice.util.Utils;
import com.dchealth.webservice.vo.response.TempCardApplyResponse;
import org.springframework.stereotype.Service;

@Service("RHC_E0012")
public class TempCardApplyService extends BaseService {
    @Override
    public Object execRequest(String message) throws Exception {

        TempCardApplyResponse tempCardApplyResponse = new TempCardApplyResponse();
        tempCardApplyResponse = Utils.getInstance(tempCardApplyResponse);
        return tempCardApplyResponse;
    }
}