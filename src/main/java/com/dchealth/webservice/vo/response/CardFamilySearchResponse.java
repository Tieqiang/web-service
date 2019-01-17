package com.dchealth.webservice.vo.response;


import com.dchealth.webservice.vo.BaseResponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CardFamilySearchResponse extends BaseResponse {

    public CardFamilySearchResponse() {
        super();
    }

    private int total;//家庭成员总数|



    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}