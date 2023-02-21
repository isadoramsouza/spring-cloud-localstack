package com.example.springcloudlocalstack.configuration.SNSConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class SNSConfiguration {
    @Value("${snsNotificationTopicName}")
    private String snsNotificationTopicName;

    public String getSnsNotificationTopicName() {
        return snsNotificationTopicName;
    }
}

