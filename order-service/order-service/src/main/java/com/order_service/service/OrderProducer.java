package com.order_service.service;

import com.order_service.dto.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Autowired
    KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendEvent(OrderEvent event) {
        kafkaTemplate.send("order-topic", event);
    }
}
