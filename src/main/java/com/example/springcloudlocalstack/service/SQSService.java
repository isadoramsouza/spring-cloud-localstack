package com.example.springcloudlocalstack.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.example.springcloudlocalstack.configuration.SQSConfiguration.SQSConfiguration;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SQSService {

    private SQSConfiguration sqsConfiguration;
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public SQSService(SQSConfiguration sqsConfiguration, AmazonSQSAsync amazonSQSAsync) {
        this.sqsConfiguration = sqsConfiguration;
        this.queueMessagingTemplate = new QueueMessagingTemplate((amazonSQSAsync));
    }

    public void sendMessage(String message) {
        this.queueMessagingTemplate.send(sqsConfiguration.getSqsQueueName(), MessageBuilder.withPayload(message).build());
    }
}
