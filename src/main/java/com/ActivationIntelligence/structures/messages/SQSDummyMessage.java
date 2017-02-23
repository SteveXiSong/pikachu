package com.ActivationIntelligence.structures.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by Steve on 2/23/17.
 */
@Data
@NoArgsConstructor
public class SQSDummyMessage {
    String msgBody;
    long timestamp;
    Map<String, String> vars;

    @JsonCreator
    SQSDummyMessage(@JsonProperty("msgBody") String msg,
            @JsonProperty("timestamp") long tm,
            @JsonProperty("vars") Map<String, String> vars) {
        this.msgBody = msg;
        this.timestamp = tm;
        this.vars = vars;
    }
}

