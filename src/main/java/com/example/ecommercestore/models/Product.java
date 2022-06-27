package com.example.ecommercestore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "product_id")
        private Long id;

        @Column(name = "product_name", unique = true)
        private String productName;

        private String category;

        private int price;

}
