package com.dchealth.webservice.vo.response;


import com.dchealth.webservice.vo.BaseResponse;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="response")
public class NewBornResponse extends BaseResponse {


    public NewBornResponse() {
        super();
    }

    private List<PersonInfo> personinfos ;


    public List<PersonInfo> getPersoninfos() {
        return personinfos;
    }

    public void setPersoninfos(List<PersonInfo> personinfos) {
        this.personinfos = personinfos;
    }
}
