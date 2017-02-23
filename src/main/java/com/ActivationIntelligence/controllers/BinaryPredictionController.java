package com.ActivationIntelligence.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.ActivationIntelligence.common.utils.Serializer;
import com.ActivationIntelligence.proxies.aws.SQSProxy;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ActivationIntelligence.common.exceptions.InvalidInputException;
import com.ActivationIntelligence.proxies.aws.BinaryPredictionModelProxy;
import com.ActivationIntelligence.proxies.aws.exceptions.ProxyException;
import com.ActivationIntelligence.structures.client.ModelUser;
import com.ActivationIntelligence.structures.messages.SQSDummyMessage;
import com.ActivationIntelligence.structures.model.binaryPrediction.BinaryPredictionInput;
import com.ActivationIntelligence.structures.model.binaryPrediction.BinaryPredictionResult;

/**
 * Created by Steve on 2/13/17.
 */
@Log4j
@RestController
public class BinaryPredictionController {

    private static final String template = "Prediction is %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final String TABLE_NAME = "modelInstanceId";

    private static final String SQS_NAME_TEST = "pikachu_getPrediction_variables_test";
    private static final String SQS_URL_TEST = "https://sqs.us-east-1.amazonaws.com/426330653730/pikachu_getPrediction_variables_test";

    @Autowired
    private SQSProxy sqsProxy;
    @Autowired
    private BinaryPredictionModelProxy binaryPredictionModelProxy;

    @GetMapping("/modelservice/getPrediction")
    public BinaryPredictionResult getPrediction (
            @RequestParam(value="modelName", defaultValue="BinaryPrediction")
            String modeName, Map<String, String> variables)
            throws ProxyException, InvalidInputException, Exception {
        log.info(String.format("Start getPrediction model name [%s]", modeName));

        Map<String, String> var = new HashMap<String, String>(){
            {
                put("email", "steve@gmail.com");
                put("modelname", modeName);
            }};

        BinaryPredictionInput input = BinaryPredictionInput.builder()
                .variables(var)
                .build();

        SQSDummyMessage msg = new SQSDummyMessage();
        msg.setMsgBody("This is msg body.");
        msg.setTimestamp(System.currentTimeMillis());
        msg.setVars(var);

        sqsProxy.sendMessage(Serializer.serialize(msg), SQS_URL_TEST);

        BinaryPredictionResult binaryPredictionResult =
                binaryPredictionModelProxy.getFakePrediction(input);
        return binaryPredictionResult;
    }

    @RequestMapping("/modelservice/readSQSmsg")
    public String ReadSQSmsg()
            throws ProxyException, InvalidInputException, Exception {

        SQSDummyMessage msgBody = Serializer.deserialize(
                sqsProxy.receiveMessage(SQS_URL_TEST, 1).get(0).getBody(),
                SQSDummyMessage.class);

        log.info(String.format("Received message body [%s].", msgBody));

        return msgBody.toString();
    }

    @GetMapping("/modelservice/getDDBTableItem")
    public String getDDBTableItem() {
        log.info("Start initing the DDB client.");
        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();

        String tableName = "pikachu_test_table";
        Map<String, AttributeValue> key = new HashMap<String, AttributeValue>() {{
            put(TABLE_NAME, new AttributeValue("abc1"));
        }};
        GetItemRequest request = new GetItemRequest()
                .withTableName(tableName)
                .withKey(key);
        GetItemResult result = ddb.getItem(request);
        log.info("Get: " + result.getItem().toString());
        return result.getItem().toString();
    }

    /**
     * curl -H "Content-Type: application/json" -X POST -d '{"id":"123","name":"Steve"}'
     * -i http://localhost:8080/addCustomerData
     */
    @PostMapping("/addCustomerData")
    public boolean addCustomerData(@RequestBody ModelUser cus) {
        log.info(String.format("Received %s", cus.toString()));
        return cus==null?false:true;
    }
}
