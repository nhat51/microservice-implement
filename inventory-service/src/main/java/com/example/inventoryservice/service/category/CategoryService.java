package com.example.inventoryservice.service.category;

import com.example.inventoryservice.entity.Category;
import com.example.inventoryservice.response.ResponseApi;

public interface CategoryService {
    ResponseApi findAll();
    ResponseApi findById(int id);
    ResponseApi save(Category category);
    ResponseApi update(int id, Category category);
    ResponseApi delete(int id);
}
