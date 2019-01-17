package com.dchealth.webservice.service;


import com.dchealth.webservice.util.Utils;
import com.dchealth.webservice.vo.response.TempCardCheckResponse;
import org.springframework.stereotype.Service;

@Service("RHC_E0013")
public class TempCardCheckService extends BaseService {

    @Override
    public Object execRequest(String message) throws Exception {
        TempCardCheckResponse tempCardCheckResponse = new TempCardCheckResponse();
        tempCardCheckResponse = Utils.getInstance(tempCardCheckResponse);
        return tempCardCheckResponse;
    }
}