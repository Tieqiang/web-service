package com.dchealth.webservice.service;


import com.dchealth.webservice.vo.response.EHCCardInfo;
import org.springframework.stereotype.Service;

@Service("RHC_E0002")
public class QcodeApplyService extends BaseService{

    @Override
    public Object execRequest(String message) throws Exception {
        EHCCardInfo cardInfo = new EHCCardInfo();
        cardInfo.setQr_code("qrCode");
        cardInfo.setQr_code_imgtext("imgtext");
        cardInfo.setQr_code_imgurl("img-url");
        return cardInfo;
    }
}