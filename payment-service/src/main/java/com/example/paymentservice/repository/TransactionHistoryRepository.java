package com.example.paymentservice.repository;

import com.example.paymentservice.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Integer> {
}
