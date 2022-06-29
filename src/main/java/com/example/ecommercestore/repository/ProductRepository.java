package com.example.ecommercestore.repository;

import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

}
