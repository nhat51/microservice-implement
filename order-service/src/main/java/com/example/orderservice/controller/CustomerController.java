package com.example.orderservice.controller;

import com.example.orderservice.entity.Customer;
import com.example.orderservice.response.ResponseApi;
import com.example.orderservice.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class CustomerController {

    @Autowired
    CustomerService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseApi> findAll(){
        return ResponseEntity.ok().body(
                new ResponseApi(HttpStatus.OK,"success",service.finAll())
        );
    }

    @RequestMapping(method = RequestMethod.POST,path = "save")
    public ResponseEntity<ResponseApi> create(@RequestBody Customer cus){
        return ResponseEntity.ok().body(
                service.save(cus)
        );
    }

    @RequestMapping(method = RequestMethod.GET,path = "{name}")
    public ResponseEntity<ResponseApi> findByName(@PathVariable String name){
        return ResponseEntity.ok().body(
                service.findByUsername(name)
        );
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "{id}")
    public ResponseEntity<ResponseApi> delete(@PathVariable int id){
        return ResponseEntity.ok().body(
                service.delete(id)
        );
    }

}
