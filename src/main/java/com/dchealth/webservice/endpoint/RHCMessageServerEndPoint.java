package com.dchealth.webservice.endpoint;


import com.cdxt.ehc.webservice.RHCMessageServerRequest;
import com.cdxt.ehc.webservice.RHCMessageServerResponse;
import com.dchealth.webservice.service.BaseService;
import com.dchealth.webservice.vo.ActionObject;
import com.dchealth.webservice.vo.BaseResponse;
import com.dchealth.webservice.vo.MessageInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;


@Endpoint
public class RHCMessageServerEndPoint implements ApplicationContextAware {


    private ApplicationContext context ;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static final String NAMESPACE_URI = "http://webservice.ehc.cdxt.com/";

    @Autowired
    private ObjectMapper objectMapper;

    //配置对外接口
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RHCMessageServer")
    @ResponsePayload
    public RHCMessageServerResponse RHCMessageServer(@RequestPayload RHCMessageServerRequest rhcMessageServerRequest) throws Exception {

        ObjectMapper xmlMapper = new XmlMapper();
        String action = rhcMessageServerRequest.getArg0();
        String message = rhcMessageServerRequest.getArg1();
        ActionObject actionObject = xmlMapper.readValue(action, ActionObject.class);
        System.out.println(objectMapper.writeValueAsString(actionObject));
        String bussinessCode = actionObject.getBussinessCode();
        BaseService baseService = (BaseService) context.getBean(bussinessCode);
        Object responseMessage = baseService.execRequest(message);

        BaseResponse baseResponse = new BaseResponse(responseMessage);

        RHCMessageServerResponse response = new RHCMessageServerResponse();
        response.setReturn(xmlMapper.writeValueAsString(baseResponse));
        return response;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
