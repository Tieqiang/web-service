package com.dchealth.webservice.service;

import com.dchealth.webservice.vo.response.OrgCardNumSearchResponse;
import org.springframework.stereotype.Service;

@Service("RHC_E0017")
public class OrgCardNumSearchService extends BaseService {
    @Override
    public Object execRequest(String message) throws Exception {
        OrgCardNumSearchResponse orgCardNumSearchResponse = new OrgCardNumSearchResponse();
        orgCardNumSearchResponse.setCount("100");
        return orgCardNumSearchResponse;
    }
}
