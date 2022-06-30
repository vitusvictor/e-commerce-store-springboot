package com.example.ecommercestore.repository;

import com.example.ecommercestore.models.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishListItem, Long> {
    Optional<List<WishListItem>> findByEmail(String email);
    Optional<WishListItem> findByEmailAndId(String email, Long id);

}
