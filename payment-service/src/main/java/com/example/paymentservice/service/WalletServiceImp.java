package com.example.paymentservice.service;

import com.example.paymentservice.entity.Wallet;
import com.example.paymentservice.repository.TransactionHistoryRepository;
import com.example.paymentservice.repository.WalletRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WalletServiceImp implements WalletService{
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Wallet getWallet(String id){
        return walletRepository.findWalletByUserId(id);
    }


}
