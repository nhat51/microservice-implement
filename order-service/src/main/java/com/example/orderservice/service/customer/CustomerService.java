package com.example.orderservice.service.customer;

import com.example.orderservice.entity.Customer;
import com.example.orderservice.response.ResponseApi;

public interface CustomerService {
    ResponseApi finAll();
    ResponseApi save(Customer customer);
    ResponseApi update(int id,Customer customer);
    ResponseApi delete(int id);
    ResponseApi findByUsername(String name);
}
