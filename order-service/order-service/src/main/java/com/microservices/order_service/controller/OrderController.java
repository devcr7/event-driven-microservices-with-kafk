package com.microservices.order_service.controller;

import com.microservices.base_domains.dto.OrderDto;
import com.microservices.base_domains.dto.OrderEvent;
import com.microservices.order_service.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping("/place")
    public String placeOrder(@RequestBody OrderDto order) {
        order.setId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent()
                .setMessage("Order placed successfully")
                .setStatus("PENDING")
                .setOrder(order);

        orderProducer.send(orderEvent);
        return "Order placed successfully";
    }
}
