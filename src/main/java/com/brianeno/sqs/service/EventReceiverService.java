package com.brianeno.sqs.service;

import com.brianeno.sqs.entity.EventEntity;
import com.brianeno.sqs.repository.EventRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class EventReceiverService {

    private final static Integer MAX_LENGTH_EVT = 255;

    private final EventRepository eventRepository;

    @SqsListener(value = "sqs-bucket-event", pollTimeoutSeconds = "20")
    public void queueListenerS3(String event) {
        log.info("Received message {}", event);
        if (event.length() > MAX_LENGTH_EVT) {
            event = event.substring(0, MAX_LENGTH_EVT);
        }
        saveEvent(event);
    }

    @SqsListener(value = "sqs-eventtest-queue", pollTimeoutSeconds = "20")
    public void queueListener(String event) {
        log.info("Received message {}", event);
        if (event.length() > MAX_LENGTH_EVT) {
            event = event.substring(0, MAX_LENGTH_EVT);
        }
        saveEvent(event);
    }

    private void saveEvent(final String eventToBeSaved) {

        UUID uuid = UUID.randomUUID();
        EventEntity eventEntity = EventEntity.builder()
            .id(uuid.toString())
            .event(eventToBeSaved)
            .dateReceived(new Timestamp(System.currentTimeMillis()))
            .build();
        EventEntity newEntity = eventRepository.save(eventEntity);
        log.info("Saved entity with id {} to database", newEntity.getId());
    }
}
