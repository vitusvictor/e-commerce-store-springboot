package com.example.ecommercestore.repository;

import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<List<WishList>> findByEmail(String email);
}
