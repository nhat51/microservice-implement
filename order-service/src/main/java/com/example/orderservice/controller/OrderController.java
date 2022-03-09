package com.example.orderservice.controller;

import com.example.orderservice.response.ResponseApi;
import com.example.orderservice.service.order.OrderService;
import com.example.orderservice.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService service;

    @RequestMapping(method = RequestMethod.GET,path = "message")
    public String order(){
        return "This is order module";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseApi> getOrders(
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "pageSize",defaultValue = "5") int pageSize)
    {
        ObjectFilter filter = ObjectFilter.ObjectFilterBuilder.anObjectFilter()
                .withPage(page)
                .withPageSize(pageSize)
                .build();
        return  ResponseEntity.ok().body(service.listOrder(filter));
    }

    @RequestMapping(method = RequestMethod.GET,path = "user")
    public ResponseEntity<?> getOrderByUserId(@RequestParam(name = "userId")int userId){
        return ResponseEntity.ok().body(service.findOrderByCustomerId(userId));
    }
/*    @PostMapping(path = "save")
    public ResponseEntity<?> save(@RequestBody Order order){

        return  ResponseEntity.ok().body(
                service.createOrder(order)
        );
    }*/
}
