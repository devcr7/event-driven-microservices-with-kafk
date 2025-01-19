package com.microservices.order_service.kafka;

import com.microservices.base_domains.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private static final Logger LOGGER = Logger.getLogger(OrderProducer.class.getName());
    private final NewTopic topic; // Since we have a single topic defined, we can use this. Otherwise, an explicit definition for sending events to a topic has to be provided.
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void send(OrderEvent orderEvent) {
        LOGGER.info("Sending order event: " + topic.name() + ": " + orderEvent);

        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
