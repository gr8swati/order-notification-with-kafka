package com.notification_service.service;

import com.notification_service.dto.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    EmailService emailService;

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consume(OrderEvent event) {
        System.out.println("order received : " + event.getOrderId());
        System.out.println("email :" + event.getEmail());

        String message = "Your order for " + event.getProduct() +
                " is successfully placed.\nQuantity: " + event.getQuantity();

        emailService.sendEmail(
                event.getEmail(),
                "Order Placed Successfully",
                message
        );
    }

}
