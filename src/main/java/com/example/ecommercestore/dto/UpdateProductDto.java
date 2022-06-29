package com.example.ecommercestore.dto;
import lombok.Data;

@Data
public class UpdateProductDto {
    private Long id;

    private String productName;

    private String category;

    private int price;
}
