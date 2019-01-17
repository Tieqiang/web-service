package com.dchealth.webservice.vo;


import com.dchealth.webservice.vo.response.EHCCardInfo;
import com.dchealth.webservice.vo.response.PersonInfo;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement(name = "response")
@XmlSeeAlso({PersonInfo.class, EHCCardInfo.class})
public class EntitiesResponse extends BaseResponse implements EntitiesResponseInterface {
    private List<Object> entities;

    @Override
    @XmlAnyElement(lax = true)
    public List<Object> getEntities() {
        return entities;
    }

    @Override
    public void setEntities(List<Object> entities) {
        this.entities = entities;
    }
}
