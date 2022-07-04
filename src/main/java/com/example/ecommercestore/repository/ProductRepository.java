package com.example.ecommercestore.repository;

import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    Optional<Product> findByProductid(Long id);
    Optional<Product> findById(Long id);


}
