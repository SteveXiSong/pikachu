package com.ActivationIntelligence.proxies.aws;

import com.ActivationIntelligence.common.exceptions.InvalidInputException;
import com.ActivationIntelligence.proxies.aws.exceptions.ProxyException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by Steve on 2/23/17.
 */
@Log4j
@Configuration
public class DDBProxyImpl implements DDBProxy {
    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public String getItem(String tableName, Map<String, AttributeValue> key)
            throws ProxyException, InvalidInputException {
        GetItemRequest request = new GetItemRequest()
                .withTableName(tableName)
                .withKey(key);
        GetItemResult result = amazonDynamoDB.getItem(request);

        log.info("DDB get item: " + result.getItem().toString());

        return result.getItem().toString();
    }

    @Override
    public <T> T loadItem(T item)
            throws ProxyException, InvalidInputException {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        T loadedItem = mapper.load(item);
        if (loadedItem == null) {
            log.warn("DDB loaded item is null");
        } else {
            log.info(String.format("DDB loaded item [%s]", loadedItem.toString()));
        }
        return loadedItem;
    }

    @Override
    public <T> void saveItem(T item)
            throws ProxyException, InvalidInputException {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        mapper.save(item);

        //log.info(String.format("DDB saved item [%]", item.toString()));
    }
}
