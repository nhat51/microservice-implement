package com.example.inventoryservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String thumbnail;
    private String status;
    private int inStock;
    @JoinColumn(name = "provider_id")
    private int providerId;
    @Column(name = "categoryId")
    private int categoryId;

    @ManyToOne
    @JoinColumn(name = "categoryId",insertable = false,updatable = false)
    private Category category;

}
