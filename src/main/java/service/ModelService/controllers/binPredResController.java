package service.ModelService.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ModelService.structures.binPredRes;

/**
 * Created by Steve on 2/13/17.
 */
@Log4j
@RestController
public class binPredResController {

    private static final String template = "Prediction is %s!";
    private final AtomicLong counter = new AtomicLong();
    //private final Logger log = org.apache.log4j.Logger.getLogger(binPredResController.class);

    @RequestMapping("/getPrediction")
    public binPredRes getPrediction(@RequestParam(value="modelName", defaultValue="BinaryPrediction")
            String modeName) {
        log.info("Start getPrediction");
        return new binPredRes(counter.incrementAndGet(), String.format(template, modeName));
    }

    @RequestMapping("/getDDBTableItem")
    public String getDDBTableItem() {
        log.info("Start initing the DDB Client.");
        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard().build();

        String tableName = "pikachu_test_table";
        Map<String, AttributeValue> key = new HashMap<String, AttributeValue>() {{
            put("modelInstanceId", new AttributeValue("abc1"));
        }};
        GetItemRequest request = new GetItemRequest().
                withTableName(tableName)
                .withKey(key);
        GetItemResult result = ddb.getItem(request);
        String ret = result.getItem().toString();
        return ret;
    }
}
