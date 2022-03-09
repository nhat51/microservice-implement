package com.example.orderservice.repository;

import com.example.orderservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByAccessToken(String access_token);
}
