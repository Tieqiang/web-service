package com.dchealth.webservice.vo.request;

import com.dchealth.webservice.vo.MessageInterface;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class TempCardCheckMessage implements MessageInterface {
    private String qr_code;//二维码|

    public TempCardCheckMessage() {
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }
}