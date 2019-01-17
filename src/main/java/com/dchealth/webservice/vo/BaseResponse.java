package com.dchealth.webservice.vo;

import com.dchealth.webservice.vo.ResponseInterface;
import com.dchealth.webservice.vo.response.EHCCardInfo;
import com.dchealth.webservice.vo.response.PersonInfo;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "response")
public class BaseResponse implements ResponseInterface {

    private String resultCode;
    private String result;
    private String desc;

    public BaseResponse() {
        this.resultCode = "0";
        this.result = "成功";
    }


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


}
