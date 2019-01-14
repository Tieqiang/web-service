package com.dchealth.webservice.vo;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "response")
public class BaseResponse {

    private String resultCode;
    private String result;
    private String desc;

    public BaseResponse() {
        this.resultCode = "0";
        this.result = "成功";
    }

    private List<Object> entities;

    @XmlElement(name = "resultCode")
    public String getResultCode() {
        return resultCode;
    }


    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    @XmlElement(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    @XmlElement(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @XmlAnyElement(lax = true)
    public List<Object> getEntities() {
        return entities;
    }

    public void setEntities(List<Object> entities) {
        this.entities = entities;
    }

}
