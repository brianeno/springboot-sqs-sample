package com.brianeno.sqs.config;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsClientConfig {

    @Value("${sqs.queue.name}")
    private String queueName;

    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String endPointUrl;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {

        // By adding endpointOverride we can point it at localstack if desired.
        return SqsAsyncClient.builder().endpointOverride(URI.create(endPointUrl)).build();
    }

    @Bean
    public SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory() {
        return SqsMessageListenerContainerFactory
            .builder()
            .configure(options -> options
                .acknowledgementMode(AcknowledgementMode.ALWAYS)
            )
            .sqsAsyncClient(sqsAsyncClient())
            .build();
    }

    @Bean("sqsQueueName")
    public String sqsQueueName() {
        return queueName;
    }
}
