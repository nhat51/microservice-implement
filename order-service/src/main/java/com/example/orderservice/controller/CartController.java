package com.example.orderservice.controller;

import com.example.orderservice.entity.CartItem;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/cart")
@CrossOrigin("*")
public class CartController {


    // xịn thì móc trong db ra.

    @Autowired
    CartService service;



    @RequestMapping(method = RequestMethod.GET,path = "hello")
    @RolesAllowed("user")
    public String findAll(){
       return "Hello babe";
    }

    @RequestMapping(method = RequestMethod.POST,path = "submitOrder")
    @RolesAllowed("user")
    public ResponseEntity<?> submitOrder(@RequestParam(name = "access_token") String access_token,@RequestBody Order order){
        return ResponseEntity.ok().body(
                service.prepareOrder(access_token,order)
        );
    }
    @RequestMapping(method = RequestMethod.POST, path = "add")
    public ResponseEntity<?> addToCart(@RequestParam(name = "access_token") String access_token, @RequestBody CartItem cartItem){
        return ResponseEntity.ok().body(
                service.addToCart(access_token,cartItem)
        );
    }

    @RequestMapping(method = RequestMethod.PUT,path = "update")
    public ResponseEntity<?> updateCart(@RequestParam(name = "access_token") String access_token, @RequestBody CartItem cartItem){
        return ResponseEntity.ok().body(
                service.update(access_token,cartItem)
        );

    }

    @RequestMapping(method = RequestMethod.DELETE,path = "remove")
    public ResponseEntity<?> remove(@RequestParam String access_token, @RequestParam int productId){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.remove(access_token,productId)
        );
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "clear")
    public ResponseEntity<?> clear(@RequestParam String access_token){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.clear(access_token)
        );
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCart(@RequestParam(name = "access_token") String accessToken){
        int accountId = 1; // lấy từ accessToken.
       /* Cart currentShoppingCart = service.getCart(accessToken);*/ // cho người dùng hiện tại.
        return ResponseEntity.ok().body(
                service.getCart(accessToken)
        );
    }
}
