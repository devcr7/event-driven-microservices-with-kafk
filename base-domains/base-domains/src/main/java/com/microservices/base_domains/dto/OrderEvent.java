package com.microservices.base_domains.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonSerialize
public class OrderEvent {
    private String message;
    private String status;
    private OrderDto order;

    public OrderEvent setMessage(String message) {
        this.message = message;
        return this;
    }

    public OrderEvent setStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderEvent setOrder(OrderDto order) {
        this.order = order;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public OrderDto getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "orderId='" + getOrder().getId() + '\'' +
                ", quantity=" + getOrder().getQty() +
                ", price=" + getOrder().getPrice() +
                '}';
    }
}
