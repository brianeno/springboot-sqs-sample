package com.brianeno.sqs.service;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class EventSenderService {

    private final SqsTemplate sqsTemplate;
    private final String sqsQueueName;

    public void sendMessage(String message) {
        log.info("Sending message {}", message);
        this.sqsTemplate.send(sqsQueueName, MessageBuilder.withPayload(message).build());
    }
}
