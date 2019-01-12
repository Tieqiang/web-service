package com.dchealth.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlMapperTest {

    private ObjectMapper objectMapper ;



    @Before
    public void setObjectMapper() throws JsonProcessingException {
        this.objectMapper = new XmlMapper();



    }

    @Test
    public void testObjectMapper() throws JsonProcessingException {

        List<UserInfo> userInfoList = new ArrayList<>();
        for(int i =0 ;i<10 ;i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setUser_name("zhangsan"+i);
            userInfo.setHealthCard("123");
            userInfo.setPassword("password"+i);
            userInfoList.add(userInfo);
        }


        String s = this.objectMapper.writeValueAsString(userInfoList);
        System.out.println(s);
    }


    @Test
    public void testWriteUserInfo() throws JsonProcessingException {
        UserInfo info = new UserInfo();
        info.setPassword("infopassword");
        info.setUser_name("user_name");
        info.setHealthCard("health_card");

        String s = this.objectMapper.writeValueAsString(info);
        System.out.println(s);
    }


    @Test
    public void testInfo() throws IOException {
        String info = "<UserInfo><user_name>user_name</user_name><password>infopassword</password><healthCard>health_card</healthCard></UserInfo>";
//        UserInfo userInfo = this.objectMapper.convertValue(info, UserInfo.class);
//        System.out.println(userInfo.getUser_name());
        UserInfo userInfo = this.objectMapper.readValue(info, UserInfo.class);
        System.out.println(userInfo.getUser_name());


    }


}
