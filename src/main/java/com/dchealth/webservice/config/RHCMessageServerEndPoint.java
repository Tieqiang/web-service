package com.dchealth.webservice.config;


import com.cdxt.ehc.webservice.RHCMessageServerRequest;
import com.cdxt.ehc.webservice.RHCMessageServerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;



@Endpoint
public class RHCMessageServerEndPoint  {


    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static final String NAMESPACE_URI = "http://webservice.ehc.cdxt.com/";

    @Autowired
    private ObjectMapper objectMapper;

    //配置对外接口
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RHCMessageServer")
    @ResponsePayload
    public RHCMessageServerResponse RHCMessageServer(@RequestPayload RHCMessageServerRequest rhcMessageServerRequest) throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(rhcMessageServerRequest);
        logger.info(s);
        RHCMessageServerResponse response= new RHCMessageServerResponse();
        response.setReturn("成功的返回内容！");
        return response;
    }

}
