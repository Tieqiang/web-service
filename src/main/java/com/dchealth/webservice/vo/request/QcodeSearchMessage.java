package com.dchealth.webservice.vo.request;

import com.dchealth.webservice.vo.MessageInterface;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class QcodeSearchMessage implements MessageInterface {
    private String id_card;//居民身份证号|
    private String id_type;//证件类型|有身份证号只需传身份证号，没有身份证号就传证件类型和证件号码
    private String id_number;//证件号码|

    public QcodeSearchMessage() {
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }
}