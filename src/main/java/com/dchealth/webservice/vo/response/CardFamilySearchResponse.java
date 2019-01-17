package com.dchealth.webservice.vo.response;


import com.dchealth.webservice.vo.EntitiesResponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class CardFamilySearchResponse extends EntitiesResponse {

    public CardFamilySearchResponse() {
        super();
    }

    private String total;//家庭成员总数|



    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}