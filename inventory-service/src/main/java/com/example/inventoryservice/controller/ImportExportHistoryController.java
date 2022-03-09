package com.example.inventoryservice.controller;

import com.example.inventoryservice.service.importExportHistory.ImportExportHistoryService;
import com.example.inventoryservice.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/importExportHistory")
public class ImportExportHistoryController {
    @Autowired
    ImportExportHistoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll( @RequestParam(name = "page",defaultValue = "1") int page,
                                      @RequestParam(name = "pageSize",defaultValue = "2") int pageSize){
        ObjectFilter filter = ObjectFilter.ObjectFilterBuilder.anObjectFilter()
                .withPage(page)
                .withPageSize(pageSize)
                .build();
        return ResponseEntity.ok().body(
               service.findAll(filter)
        );
    }

    @RequestMapping(method = RequestMethod.GET, path = "provider")
    public ResponseEntity<?> findByProvider(@RequestParam(name = "id") int providerId){
        return ResponseEntity.ok().body(
                service.findByProvider(providerId)
        );
    }
}
