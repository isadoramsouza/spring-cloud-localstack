package com.example.springcloudlocalstack.configuration.SQSConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class SQSConfiguration {
    @Value("${sqsQueueName}")
    private String sqsQueueName;

    public String getSqsQueueName() {
        return sqsQueueName;
    }
}

