package com.example.orderservice.service.order;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderDetail;
import com.example.orderservice.response.ResponseApi;
import com.example.orderservice.specification.ObjectFilter;

public interface OrderService {
    ResponseApi listOrder(ObjectFilter objectFilter);
    Order createOrder(String access_token, Order order);
    ResponseApi findOrder(int id);
    ResponseApi findOrderByCustomerId(int customerId);
    ResponseApi createOrderDetail(OrderDetail orderDetail);
    ResponseApi deleteOrder(int order);

}
