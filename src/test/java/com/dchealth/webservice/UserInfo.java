package com.dchealth.webservice;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
@XmlRootElement(name="user")
public class UserInfo implements Serializable {
    private String user_name ;
    private String password;
    private String healthCard;

    public UserInfo() {
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }
}
