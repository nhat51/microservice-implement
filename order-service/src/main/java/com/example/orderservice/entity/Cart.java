package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "access_token")
    private String accessToken;
    private double totalPrice;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private Set<CartItem> items;

    @JsonIgnore
    public int getTotal(){
        Set<CartItem> list = this.getItems();
        int total = 0;
        for(CartItem cartiem : list){
            total +=cartiem.getUnitPrice() * cartiem.getQuantity();
        }
        return total;
    }
    @JsonIgnore
    public void setTotalMoney(){
        this.totalPrice = getTotal();
    }
}
