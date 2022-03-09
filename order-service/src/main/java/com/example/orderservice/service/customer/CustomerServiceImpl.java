package com.example.orderservice.service.customer;

import com.example.orderservice.entity.Customer;
import com.example.orderservice.repository.CustomerRepository;
import com.example.orderservice.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public ResponseApi finAll() {
        return new ResponseApi(HttpStatus.OK,"success",customerRepository.findAll());
    }

    @Override
    public ResponseApi save(Customer customer) {
        return new ResponseApi(HttpStatus.CREATED,"success",customerRepository.save(customer));
    }

    @Override
    public ResponseApi update(int id, Customer customer) {
        Customer exist = customerRepository.getById(id);
        exist.setName(customer.getName());
        exist.setEmail(customer.getEmail());
        exist.setPhone(customer.getPhone());
        exist.setUsername(customer.getUsername());
        exist.setPassword(customer.getPassword());
        return new ResponseApi(HttpStatus.OK,"success",customerRepository.save(exist));
    }

    @Override
    public ResponseApi delete(int id) {
        Customer cus = customerRepository.getById(id);
        customerRepository.delete(cus);
        return new ResponseApi(HttpStatus.OK,"success",true);
    }

    @Override
    public ResponseApi findByUsername(String name) {
        return new ResponseApi(HttpStatus.OK,"success",customerRepository.findCustomerByUsername(name));
    }
}
