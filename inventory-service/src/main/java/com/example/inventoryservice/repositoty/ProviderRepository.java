package com.example.inventoryservice.repositoty;

import com.example.inventoryservice.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider,Integer> {
}
