package com.microservices.stock_service.kafka;

import com.microservices.base_domains.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private static final Logger LOGGER = Logger.getLogger(OrderConsumer.class.getName());

    @KafkaListener(topics = "${spring.kafka.topics.order.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info("Consuming order event in stock service: " + orderEvent.toString());

        // We can make the database save calls here, either in batches (bulk save) or as scheduled saves.
        // We'll maintain the list at the class level and, based on its size or the scheduled time, flush the data to the database.
    }
}
