package com.ActivationIntelligence.proxies.aws;

import com.ActivationIntelligence.common.exceptions.InvalidInputException;
import com.ActivationIntelligence.proxies.aws.exceptions.ProxyException;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by Steve on 2/23/17.
 */
@Log4j
@Configuration
public class SQSProxyImpl implements SQSProxy {
    @Autowired
    private AmazonSQS amazonSQS;

    @Override
    public void sendMessage(@NonNull final String messageBody, @NonNull String queueURL)
            throws ProxyException, InvalidInputException {
        SendMessageRequest request = new SendMessageRequest();
        request.setMessageBody(messageBody);
        request.setQueueUrl(queueURL);

        SendMessageResult result = amazonSQS.sendMessage(request);

        log.info(String.format("Send SQS message to [%s] is successful.", queueURL));
        return;
    }

    @Override
    public List<Message> receiveMessage(@NonNull String queueUrl, @NonNull int numOfMsg)
            throws ProxyException, InvalidInputException {

        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
        receiveMessageRequest.setQueueUrl(queueUrl);
        receiveMessageRequest.setMaxNumberOfMessages(numOfMsg);
        ReceiveMessageResult result = amazonSQS.receiveMessage(receiveMessageRequest);

        log.info("Received: " + result.toString());
        return result.getMessages();
    }
}
