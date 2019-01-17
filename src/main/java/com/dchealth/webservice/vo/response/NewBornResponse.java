package com.dchealth.webservice.vo.response;


import com.dchealth.webservice.vo.BaseResponse;
import com.dchealth.webservice.vo.EntitiesResponseInterface;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement(name="response")
@XmlSeeAlso({PersonInfo.class})
public class NewBornResponse extends BaseResponse implements EntitiesResponseInterface {


    public NewBornResponse() {
        super();
    }

   private List<Object> entities;

    @Override
    @XmlAnyElement(lax = true)
    @XmlElementWrapper(name="personinfos")
    public List<Object> getEntities() {
        return entities;
    }

    @Override
    public void setEntities(List<Object> entities) {
        this.entities = entities;
    }
}
