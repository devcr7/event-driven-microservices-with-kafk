package com.microservices.email_service.kafka;

import com.microservices.base_domains.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private static final Logger LOGGER = Logger.getLogger(OrderConsumer.class.getName());
    private final JavaMailSender mailSender;

    @KafkaListener(topics = "${spring.kafka.topics.order.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info("Consuming order event in email service: " + orderEvent.toString());
        sendEmail(orderEvent);
        // We can make the database save calls here, either in batches (bulk save) or as scheduled saves.
        // We'll maintain the list at the class level and, based on its size or the scheduled time, flush the data to the database.
    }

    private void sendEmail(OrderEvent orderEvent) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("xyz@gmail.com"); // Replace with the recipient's email address
        message.setSubject("New Order Event");
        message.setText("Order details: " + orderEvent.toString());

        mailSender.send(message);
        LOGGER.info("Order event email sent: " + orderEvent.toString());
    }
}

//a new consumer with a new group ID and associate it with an existing topic, all previously published messages in the topic will be sent to this new consumer
