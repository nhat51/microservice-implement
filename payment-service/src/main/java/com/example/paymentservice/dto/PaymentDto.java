package com.example.paymentservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDto {
    private int orderId;
    private String userId;
    private String paymentStatus;
    private String message;

    public PaymentDto(int orderId,String userId){
        this.orderId = orderId;
        this.userId = userId;
    }
}
