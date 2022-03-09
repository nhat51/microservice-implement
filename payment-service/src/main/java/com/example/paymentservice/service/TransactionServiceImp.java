package com.example.paymentservice.service;

import com.example.paymentservice.entity.TransactionHistory;
import com.example.paymentservice.repository.TransactionHistoryRepository;
import com.example.paymentservice.response.ResponseApi;
import com.example.paymentservice.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImp implements TransactionService{
    @Autowired
    TransactionHistoryRepository historyRepository;

    @Override
    public ResponseApi getAll(ObjectFilter objectFilter) {
        PageRequest paging = PageRequest.of(objectFilter.getPage()-1, objectFilter.getPageSize());
        Page<TransactionHistory> data = historyRepository.findAll(paging);
        return new ResponseApi(HttpStatus.OK,"success",data);
    }
}
