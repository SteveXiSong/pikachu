package com.ActivationIntelligence.proxies.aws;

import com.ActivationIntelligence.common.exceptions.InvalidInputException;
import com.ActivationIntelligence.proxies.aws.exceptions.ProxyException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.Map;

/**
 * Created by Steve on 2/23/17.
 */
public interface DDBProxy {
    String getItem(String tableName, Map<String, AttributeValue> key)
            throws ProxyException, InvalidInputException;
    <T> T loadItem(T item)
            throws ProxyException, InvalidInputException;
    <T> void saveItem(T item)
            throws ProxyException, InvalidInputException;
}
