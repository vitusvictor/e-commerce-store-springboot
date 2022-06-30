package com.example.ecommercestore.repository;

import com.example.ecommercestore.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    Optional<List<CartItem>> findByEmail(String email);
    Optional<CartItem> findByEmailAndId(String id, Long email);
}
