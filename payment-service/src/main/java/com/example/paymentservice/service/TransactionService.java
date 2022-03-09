package com.example.paymentservice.service;

import com.example.paymentservice.response.ResponseApi;
import com.example.paymentservice.specification.ObjectFilter;

public interface TransactionService {
    ResponseApi getAll(ObjectFilter objectFilter);
}
