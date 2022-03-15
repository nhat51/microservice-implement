package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.Provider;
import com.example.inventoryservice.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/providers")
@CrossOrigin("*")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<Object>(providerService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/save")
    public ResponseEntity<Object> save(@RequestBody Provider provider){
        return new ResponseEntity<Object>(providerService.save(provider), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,path = "detail")
    public ResponseEntity<Object> findById(@RequestParam(name = "id") int id){
        return new ResponseEntity<Object>(providerService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,path = "update")
    public ResponseEntity<Object> update(@RequestParam int id, @RequestBody Provider provider){
        return new ResponseEntity<Object>(providerService.update(id,provider), HttpStatus.OK);
    }
}
