package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.Category;
import com.example.inventoryservice.response.ResponseApi;
import com.example.inventoryservice.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    @RequestMapping(method = RequestMethod.GET,path = "message")
    public String iventory(){
        return "This is inventory module";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseApi> getAll(){
        return ResponseEntity.ok().body(
                service.findAll()
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseApi> save(@RequestBody Category category){
        return ResponseEntity.ok().body(
                service.save(category)
        );
    }
}
