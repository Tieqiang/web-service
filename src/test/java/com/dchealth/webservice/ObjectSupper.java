package com.dchealth.webservice;

import java.util.ArrayList;
import java.util.List;

public class ObjectSupper {

    private String operationDescription ;

    private String name ;
    private String code ;

    private List<FieldBean> messageBean = new ArrayList<>();
    private String requestDemo ;

    private List<FieldBean> responseBean= new ArrayList<>();

    private String responseName ;

    private String responseDemo ;

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldBean> getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(List<FieldBean> messageBean) {
        this.messageBean = messageBean;
    }

    public List<FieldBean> getResponseBean() {
        return responseBean;
    }

    public void setResponseBean(List<FieldBean> responseBean) {
        this.responseBean = responseBean;
    }

    public String getResponseName() {
        return responseName;
    }

    public void setResponseName(String responseName) {
        this.responseName = responseName;
    }


    public String getRequestDemo() {
        return requestDemo;
    }

    public void setRequestDemo(String requestDemo) {
        this.requestDemo = requestDemo;
    }

    public String getResponseDemo() {
        return responseDemo;
    }

    public void setResponseDemo(String responseDemo) {
        this.responseDemo = responseDemo;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
