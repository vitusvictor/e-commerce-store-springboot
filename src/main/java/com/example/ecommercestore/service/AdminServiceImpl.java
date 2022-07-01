package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.dto.UpdateUserDto;
import com.example.ecommercestore.exception.CustomAppException;
import com.example.ecommercestore.models.CartItem;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.WishListItem;
import com.example.ecommercestore.repository.CartRepository;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.repository.AdminRepository;
import com.example.ecommercestore.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, ProductRepository productRepository) {
        this.adminRepository = adminRepository;
        this.productRepository = productRepository;

    }

    @Override
    public List<User> getAllUsers() {
        return adminRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) {

        Product product = new Product();

        product.setProductName(productDto.getProductName());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Product product1 = productRepository.findById(id)
                .orElseThrow(()-> new CustomAppException("Product doesn't exit."));

        product1.setProductName(product.getProductName());
        product1.setCategory(product.getCategory());
        product1.setPrice(product.getPrice());

        return productRepository.save(product1);
    }

    @Override
    public User getUser(Long id) {
        User user = adminRepository.findById(id).orElseThrow(()-> new CustomAppException("User doesn't exist!"));

        return user;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("Product does not exist."));

        productRepository.delete(product);
    }
}
