package com.dchealth.webservice.service;

import com.dchealth.webservice.vo.ActionObject;

public abstract class BaseService {



    public abstract Object execRequest(String message) throws Exception;

    public Object getResponse(ActionObject actionObject,String message) throws Exception {
        return execRequest(message);
    }

}
