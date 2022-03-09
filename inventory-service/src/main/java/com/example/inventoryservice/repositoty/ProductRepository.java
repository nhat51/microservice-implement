package com.example.inventoryservice.repositoty;

import com.example.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    @Query("select p from Product p where p.name like %:name%")
    Product search(String name);
    Product findProductByName(String name);
    List<Product> findProductsByCategoryId(int id);

 }
