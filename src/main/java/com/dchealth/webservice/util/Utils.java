package com.dchealth.webservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {

    public static String getJsonString(Class c) throws IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException {

        ObjectMapper objectMapper = new ObjectMapper();
        Object o = c.newInstance();

        Method[] declaredMethods = c.getDeclaredMethods();

        for (Method method:declaredMethods){
            String name = method.getName();
            if(name.startsWith("set")){
                method.invoke(o,name.substring(3));
            }
        }

        return objectMapper.writeValueAsString(o);
    }


}
