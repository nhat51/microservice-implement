package com.example.orderservice.entityDTO;

import com.example.orderservice.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private int orderId;
    private int productId;
    private String productName;
    private double unitPrice;
    private int quantity;

    public OrderDetailDTO(OrderDetail orderDetail){
        this.orderId = orderDetail.getOrderId();
        this.productId = orderDetail.getProductId();
        this.productName = orderDetail.getProduct_name();
        this.unitPrice = orderDetail.getUnitPrice();
        this.quantity = orderDetail.getQuantity();
    }
}
