package com.dchealth.webservice.endpoint;


import com.cdxt.ehc.webservice.RHCMessageServerRequest;
import com.cdxt.ehc.webservice.RHCMessageServerResponse;
import com.dchealth.webservice.service.BaseService;
import com.dchealth.webservice.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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


import javax.xml.bind.JAXB;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


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
        String action = rhcMessageServerRequest.getArg0();
        String message = rhcMessageServerRequest.getArg1();
        ActionObject actionObject =JAXB.unmarshal(new StreamSource(new StringReader(action)), ActionObject.class);
//        ActionObject actionObject =(ActionObject) unmarshaller.unmarshal(new StringReader(action));
        System.out.println(objectMapper.writeValueAsString(actionObject));

        String bussinessCode = actionObject.getBussinessCode();
        BaseService baseService = (BaseService) context.getBean(bussinessCode);
        Object responseMessage = baseService.execRequest(message);
        ResponseInterface baseResponse = null ;
        if(responseMessage instanceof ResponseInterface){
            baseResponse = (ResponseInterface) responseMessage;
        }else{
            baseResponse =new EntitiesResponse();
            List<Object> list = new ArrayList<>();
            list.add(responseMessage);
            ((EntitiesResponse)baseResponse).setEntities(list);
        }
        StringWriter writer = new StringWriter();
        JAXB.marshal(baseResponse,writer);
//        marshaller.marshal(baseResponse,writer);

        RHCMessageServerResponse response = new RHCMessageServerResponse();
        response.setReturn(writer.toString());
        return response;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
