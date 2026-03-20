package com.order_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private String email;
    private String status;
    private String product;
    private int quantity;
}
