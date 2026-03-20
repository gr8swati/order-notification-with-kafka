package com.order_service.service;

import com.order_service.dto.OrderEvent;
import com.order_service.entity.Order;
import com.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    public Order create(Order order) {
        Order saved = orderRepository.save(order);

        OrderEvent event = new OrderEvent(
                                    saved.getId(),
                                    saved.getEmail(),
                                    saved.getStatus(),
                                    saved.getProduct(),
                                    saved.getQuantity());

        orderProducer.sendEvent(event);
        return saved;
    }
}
