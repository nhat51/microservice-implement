package com.example.paymentservice.controller;
import com.example.paymentservice.service.TransactionService;
import com.example.paymentservice.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactionHistory")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(method = RequestMethod.GET,path = "message")
    public String payment(){
        return "This is payment module";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll( @RequestParam(name = "page",defaultValue = "1") int page,
                                      @RequestParam(name = "pageSize",defaultValue = "5") int pageSize){
        ObjectFilter filter = ObjectFilter.ObjectFilterBuilder.anObjectFilter()
                .withPage(page)
                .withPageSize(pageSize)
                .build();
        return ResponseEntity.ok().body(
                transactionService.getAll(filter)
        );
    }
}
