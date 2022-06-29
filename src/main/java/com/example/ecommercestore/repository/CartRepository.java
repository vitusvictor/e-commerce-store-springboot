package com.example.ecommercestore.repository;

import com.example.ecommercestore.models.Cart;
import com.example.ecommercestore.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<List<Cart>> findByEmail(String email);
    void findByEmailAndId(String id, Long email);
}
