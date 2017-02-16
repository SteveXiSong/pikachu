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
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ModelService.structures.binPredRes;
import service.ModelService.structures.Customer;

/**
 * Created by Steve on 2/13/17.
 */
@Log4j
@RestController
public class binPredResController {

    private static final String template = "Prediction is %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final String TABLE_NAME = "modelInstanceId";

    @RequestMapping("/getPrediction")
    public binPredRes getPrediction(@RequestParam(value="modelName", defaultValue="BinaryPrediction")
            String modeName) {
        log.info("Start getPrediction");
        return new binPredRes(counter.incrementAndGet(), String.format(template, modeName));
    }

    @RequestMapping("/getDDBTableItem")
    public Customer getDDBTableItem() {
        log.info("Start initing the DDB Client.");
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
        return new Customer("123", "Steve");
    }

    /**
     * curl -H "Content-Type: application/json" -X POST -d '{"id":"123","name":"Steve"}'
     * -i http://localhost:8080/addCustomerData
     */
    @PostMapping("/addCustomerData")
    public boolean addCustomerData(@RequestBody Customer cus) {
        log.info(String.format("Received %s", cus.toString()));
        return cus==null?false:true;
    }
}
