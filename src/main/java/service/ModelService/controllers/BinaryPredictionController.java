package service.ModelService.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ModelService.common.exceptions.InvalidInputException;
import service.ModelService.proxies.aws.BinaryPredictionModelProxy;
import service.ModelService.proxies.aws.exceptions.ProxyException;
import service.ModelService.structures.client.ModelClient;
import service.ModelService.structures.model.binaryPrediction.BinaryPredictionInput;
import service.ModelService.structures.model.binaryPrediction.BinaryPredictionResult;

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
    private BinaryPredictionModelProxy binaryPredictionModelProxy;

    @GetMapping("/getPrediction")
    public BinaryPredictionResult getPrediction (
            @RequestParam(value="modelName", defaultValue="BinaryPrediction")
            String modeName, Map<String, String> variables)
            throws ProxyException, InvalidInputException {
        log.info(String.format("Start getPrediction model name [%s]", modeName));

        BinaryPredictionInput input = BinaryPredictionInput.builder()
                .variables(new HashMap<String, String>(){{
                    put("email", "steve@gmail.com");
                    put("modelname", modeName);
                }})
                .build();

        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        SendMessageRequest request = new SendMessageRequest();
        request.setMessageBody(input.toString());
        request.setQueueUrl(SQS_URL_TEST);
        SendMessageResult result = sqs.sendMessage(request);
        //log.info(String.format("sent message. message id [%d]", result.getMessageId()));

        BinaryPredictionResult binaryPredictionResult = binaryPredictionModelProxy.getFakePrediction(input);
        return binaryPredictionResult;
    }

    @GetMapping("/getDDBTableItem")
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
        return "Steve";
    }

    /**
     * curl -H "Content-Type: application/json" -X POST -d '{"id":"123","name":"Steve"}'
     * -i http://localhost:8080/addCustomerData
     */
    @PostMapping("/addCustomerData")
    public boolean addCustomerData(@RequestBody ModelClient cus) {
        log.info(String.format("Received %s", cus.toString()));
        return cus==null?false:true;
    }
}
