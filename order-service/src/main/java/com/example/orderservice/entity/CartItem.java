package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_id")
    private Integer productId;
    private String productName;
    private String thumbnail;
    private double unitPrice; // giá tại thời điểm mua, sau đó giá sản phẩm có thể thay đổi.
    private int quantity;
    @Column(name = "cart_id")
    private Integer cartId;

    @ManyToOne()
    @JoinColumn(name = "cart_id",insertable = false,updatable = false)
    @JsonIgnore
    private Cart cart;
}
