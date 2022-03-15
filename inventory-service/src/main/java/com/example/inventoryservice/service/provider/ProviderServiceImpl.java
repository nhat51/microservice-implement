package com.example.inventoryservice.service.provider;

import com.example.inventoryservice.entity.Provider;
import com.example.inventoryservice.repositoty.ProviderRepository;
import com.example.inventoryservice.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService{

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public ResponseApi getAll() {
        return new ResponseApi(HttpStatus.OK,"Success",providerRepository.findAll());
    }

    @Override
    public ResponseApi findById(int id) {
        return new ResponseApi(HttpStatus.OK,"Success",providerRepository.getById(id));
    }

    @Override
    public ResponseApi update(int id, Provider provider) {
        Optional<Provider> exist = providerRepository.findById(id);
        if (exist.isPresent()){
            exist.get().setAddress(provider.getAddress());
            exist.get().setEmail(provider.getEmail());
            exist.get().setName(provider.getName());
            exist.get().setPhone(provider.getPhone());
            exist.get().setStatus(provider.getStatus());
            return new ResponseApi(HttpStatus.OK, "Success", exist);
        }
        return new ResponseApi(HttpStatus.NOT_FOUND,"Provider not found","");
    }

    @Override
    public ResponseApi save(Provider provider) {
        return new ResponseApi(HttpStatus.CREATED,"Created",providerRepository.save(provider));
    }
}
