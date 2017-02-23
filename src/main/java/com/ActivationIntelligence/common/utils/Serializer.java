package com.ActivationIntelligence.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;

/**
 * Created by Steve on 2/22/17.
 */
@Log4j
public class Serializer {
    public static <T> String serialize(T msg) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String serMsg;
        try {
            serMsg = mapper.writeValueAsString(msg);
        } catch (Exception e) {
            log.error(String.format("Error when serializing object %s.",
                    msg.toString()));
            throw e;
        }
        return serMsg;
    }

    public static <T> T deserialize(String serMsg, Class<T> type) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        T deserObj;
        try {
            deserObj = mapper.readValue(serMsg, type);
        } catch (Exception e) {
            log.error(String.format("Error when de-serializing object %s to type %s.",
                    serMsg.toString(), type.toString()));
            throw e;
        }
        return deserObj;
    }
}
