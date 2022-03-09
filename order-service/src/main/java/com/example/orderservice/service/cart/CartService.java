package com.example.orderservice.service.cart;

import com.example.orderservice.entity.CartItem;
import com.example.orderservice.entity.Order;
import com.example.orderservice.response.ResponseApi;

public interface CartService {
        ResponseApi addToCart(String access_token, CartItem cartItem);
        ResponseApi update(String access_token,CartItem cartItem);
        ResponseApi remove(String access_token,int productId);
        ResponseApi clear(String access_token);
        ResponseApi getCart(String access_token);
        ResponseApi prepareOrder(String access_token, Order order);
        double calculateTotalPrice();
}
