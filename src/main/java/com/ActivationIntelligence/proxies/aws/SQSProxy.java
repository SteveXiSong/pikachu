package com.ActivationIntelligence.proxies.aws;

import com.ActivationIntelligence.common.exceptions.InvalidInputException;
import com.ActivationIntelligence.proxies.aws.exceptions.ProxyException;
import com.amazonaws.services.sqs.model.Message;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by Steve on 2/23/17.
 */
public interface SQSProxy {
    void sendMessage(String messageBody, String queueUrl)
            throws ProxyException, InvalidInputException;

    List<Message> receiveMessage(String queueUrl, int numOfMsg)
            throws ProxyException, InvalidInputException;

}
