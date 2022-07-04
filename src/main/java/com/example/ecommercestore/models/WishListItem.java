package com.example.ecommercestore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wishlist")
public class WishListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    private Long productid;
    private String email;
    private String productName;
    private String category;
    private int price;

}
