package com.ActivationIntelligence.beans;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Steve on 2/23/17.
 */
@Setter
@Configuration
public class AwsDDBFactoryBean implements FactoryBean<AmazonDynamoDB> {
    private Regions awsRegion;

    public AmazonDynamoDB amazonDynamoDB() throws Exception {
        return getObject();
    }

    @Override
    public AmazonDynamoDB getObject() throws Exception {
        if (awsRegion != null) {
            return AmazonDynamoDBClientBuilder
                    .standard()
                    .withRegion(awsRegion)
                    .build();
        }
        return AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Override
    public Class<? extends AmazonDynamoDB> getObjectType() {
        return AmazonDynamoDB.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
