package com.example.inventoryservice.service.category;

import com.example.inventoryservice.entity.Category;
import com.example.inventoryservice.repositoty.CategoryRepository;
import com.example.inventoryservice.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public ResponseApi findAll() {
        return new ResponseApi(HttpStatus.OK,"success",categoryRepository.findAll());
    }

    @Override
    public ResponseApi findById(int id) {
        return new ResponseApi(HttpStatus.OK,"success",categoryRepository.getById(id));
    }

    @Override
    public ResponseApi save(Category category) {
        return new ResponseApi(HttpStatus.CREATED,"success",categoryRepository.save(category));
    }

    @Override
    public ResponseApi update(int id, Category category) {
        Category exist = categoryRepository.getById(id);
        exist.setName(category.getName());
        return new ResponseApi(HttpStatus.OK,"success",categoryRepository.save(exist));
    }

    @Override
    public ResponseApi delete(int id) {
        Category exist = categoryRepository.getById(id);
        categoryRepository.delete(exist);
        return new ResponseApi(HttpStatus.OK,"success",true);
    }
}
