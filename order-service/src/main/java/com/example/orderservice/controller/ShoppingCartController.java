package com.example.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ShoppingCartController {

//    public static HashMap<Integer, ShoppingCart> listShoppingCart; // bựa
    // xịn thì móc trong db ra.
//
//    @Autowired
//    CartService service;


//    @RequestMapping(method = RequestMethod.GET,path = "list")
//    public List<Cart> findAll(){
//       return service.findAll();
//    }

  /*  @RequestMapping(method = RequestMethod.POST, path = "add")
    public Cart addToCart(@RequestParam(name = "access_token") String access_token, @RequestBody CartItem cartItem){
        return service.addToCart(access_token,cartItem);
    }*/



/*    @RequestMapping(method = RequestMethod.GET)
    public ShoppingCart getCart(@RequestHeader String accessToken){
        int accountId = 1; // lấy từ accessToken.
        ShoppingCart currentShoppintCart = null; // cho người dùng hiện tại.
        if(listShoppingCart.containsKey(accountId)){
            currentShoppintCart = listShoppingCart.get(accountId);
        }else{
            currentShoppintCart = new ShoppingCart();
        }
        return currentShoppintCart;
    }*/
}
