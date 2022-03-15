package com.example.orderservice.entityDTO;

import com.example.orderservice.entity.Order;
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

    public OrderDto(Order order){
        this.orderId = order.getId();
        this.customerId = order.getCustomerId();
        this.totalPrice = order.getTotalPrice();
        this.orderStatus = order.getStatus();
        this.paymentStatus = order.getPayment_status();
        this.inventoryStatus = order.getInventory_status();
        this.customerName = order.getCustomerName();
        order.getOrderDetails().forEach(orderDetail -> {
            this.orderDetails.add(new OrderDetailDTO(orderDetail));
        });
    }
}
