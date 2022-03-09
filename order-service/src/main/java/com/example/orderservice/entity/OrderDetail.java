package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;
    @Column(name = "order_Id",updatable = false,insertable = false)
    private int orderId;
    @Column(name = "product_Id",updatable = false,insertable = false)
    private int productId;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "quantity")
    private int quantity;
    private String product_name;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_Id")
    @JsonIgnore
    private Order order;
}
