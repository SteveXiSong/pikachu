package com.ActivationIntelligence.structures.items;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.Map;

/**
 * Created by Steve on 2/23/17.
 */
@Data
@DynamoDBTable(tableName = "pikachu_test_table")
public class DDBDummyItem {
    @DynamoDBHashKey
    private String modelInstanceId;
    @DynamoDBAttribute
    private long timestamp;
    @DynamoDBAttribute
    private int number;
    @DynamoDBAttribute
    private Map<String, String> map;
}
