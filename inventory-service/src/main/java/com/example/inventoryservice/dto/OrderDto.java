package com.example.inventoryservice.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private int orderId;
    private String customerId;
    private String customerName;
    private double totalPrice;
    private String paymentStatus;
    private String inventoryStatus;
    private String orderStatus;
    private String message;
    private Set<OrderDetailDTO> orderDetails = new HashSet<>();

}
