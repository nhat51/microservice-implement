package com.example.inventoryservice.service.product;

import com.example.inventoryservice.entity.Product;
import com.example.inventoryservice.response.ResponseApi;
import com.example.inventoryservice.specification.ObjectFilter;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAll(ObjectFilter filter);
    ResponseApi findByCategory(String name);
    ResponseApi findByName(String name);
    ResponseApi getById(int id);
    ResponseApi save(Product product);
    ResponseApi delete(int id);
    ResponseApi update(int id, Product product);
}
