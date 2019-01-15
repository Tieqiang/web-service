package com.dchealth.webservice.vo.response;


import com.dchealth.webservice.vo.BaseResponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TempCardApplyResponse extends BaseResponse {

    private String qr_code;//二维码内容|
    private String qr_code_imgtext;//二维码图片编码|二维码图片base64位编码，可以解码成图片 ,图片大小像素200*200

    public TempCardApplyResponse() {
    }



    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getQr_code_imgtext() {
        return qr_code_imgtext;
    }

    public void setQr_code_imgtext(String qr_code_imgtext) {
        this.qr_code_imgtext = qr_code_imgtext;
    }
}