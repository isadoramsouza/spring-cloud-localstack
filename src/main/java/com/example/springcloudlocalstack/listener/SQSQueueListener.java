package com.example.springcloudlocalstack.listener;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SQSQueueListener {

    @SqsListener("${sqsQueueName}")
    public void queueListener(String message) {
        try {
            log.info("Receiving message: " + message);
        } catch (Exception ex) {
            log.error("Error receiving message, " + ex.getMessage());
        }
    }
}
