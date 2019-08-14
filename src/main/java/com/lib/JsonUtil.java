package com.lib;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lib.exception.SystemException;

public class JsonUtil {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static String toJSONString(Object object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new SystemException(e,"序列化异常");
        }
    }
}
