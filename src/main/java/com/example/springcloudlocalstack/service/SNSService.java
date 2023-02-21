package com.example.springcloudlocalstack.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.example.springcloudlocalstack.configuration.SNSConfiguration.SNSConfiguration;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SNSService {

    private SNSConfiguration snsConfiguration;
    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    public SNSService(SNSConfiguration snsConfiguration, AmazonSNS amazonSns) {
        this.snsConfiguration = snsConfiguration;
        this.notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSns);
    }

    public void sendNotification(Object message, Map<String, Object> headers) {
        this.notificationMessagingTemplate.convertAndSend(snsConfiguration.getSnsNotificationTopicName(), message, headers);
    }
}
