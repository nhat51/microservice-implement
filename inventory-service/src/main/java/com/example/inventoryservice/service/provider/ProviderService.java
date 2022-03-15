package com.example.inventoryservice.service.provider;

import com.example.inventoryservice.entity.Provider;
import com.example.inventoryservice.response.ResponseApi;

public interface ProviderService {
    ResponseApi getAll();
    ResponseApi findById(int id);
    ResponseApi update(int id, Provider provider);
    ResponseApi save( Provider provider);
}
