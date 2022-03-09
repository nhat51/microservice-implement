package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ship_name")
    private String shipName;
    @Column(name = "ship_address")
    private String shipAddress;
    @Column(name = "status")
    private String status;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "created_at")
    private LocalDate created_at;
    @Column(name = "updated_at")
    private LocalDate updated_at;
    private String payment_status;
    private String inventory_status;
    @ManyToOne
    @JoinColumn(name = "customer_id",insertable = false,updatable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();

}
