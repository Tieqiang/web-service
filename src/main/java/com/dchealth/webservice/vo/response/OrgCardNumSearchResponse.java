package com.dchealth.webservice.vo.response;


import com.dchealth.webservice.vo.BaseResponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class OrgCardNumSearchResponse extends BaseResponse {

    private String count ;

    public OrgCardNumSearchResponse() {
        super();
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
