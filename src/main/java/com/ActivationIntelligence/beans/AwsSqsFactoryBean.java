package com.ActivationIntelligence.beans;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Steve on 2/22/17.
 */
@Setter
@Configuration
@NoArgsConstructor
public class AwsSqsFactoryBean implements FactoryBean<AmazonSQS> {
    private Regions region;

    public AmazonSQS amazonSQS() throws Exception {
        return getObject();
    }

    @Override
    public AmazonSQS getObject() throws Exception {
        return AmazonSQSClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Override
    public Class<? extends AmazonSQS> getObjectType() {
        return AmazonSQS.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
