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

//    @Override
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    @Override
    public Product createProduct(ProductDto productDto) {

        Product product = new Product();

        product.setProductName(productDto.getProductName());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());

        return productRepository.save(product);
    }

//    @Override
//    public WishListItem addToWishList(Long id, String email) {
//        Product product1 = productRepository.findById(id)
//                .orElseThrow(()-> new CustomAppException("Product not found!"));
//
//        WishListItem wishListItem = new WishListItem();
//
//        wishListItem.setProductName(product1.getProductName());
//        wishListItem.setCategory(product1.getCategory());
//        wishListItem.setPrice(product1.getPrice());
//        wishListItem.setEmail(email);
//
//        return wishListRepository.save(wishListItem);
//    }

//    @Override
//    public CartItem addToCart(Long id, String email) {
//        Product product1 = productRepository.findById(id)
//                .orElseThrow(()-> new CustomAppException("Product not found!"));
//
//        CartItem cartItem = new CartItem();
//        System.out.println("printing from addToCart user implementation" + email);
//        cartItem.setProductName(product1.getProductName());
//        cartItem.setCategory(product1.getCategory());
//        cartItem.setPrice(product1.getPrice());
//        cartItem.setEmail(email);
//
//        return cartRepository.save(cartItem);
//    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Product product1 = productRepository.findById(id)
                .orElseThrow(()-> new CustomAppException("Product doesn't exit."));

        product1.setProductName(product.getProductName());
        product1.setCategory(product.getCategory());
        product1.setPrice(product.getPrice());

        return productRepository.save(product1);
    }

//    @Override
//    public List<WishListItem> getWishList(String email) {
//        return wishListRepository.findByEmail(email).orElseThrow(()-> new CustomAppException("Nothing found!"));
//    }

//    @Override
//    public List<CartItem> getCart(String email) {
//        return  cartRepository.findByEmail(email).orElseThrow(()-> new CustomAppException("Nothing found!"));
//    }

    @Override
    public User getUser(Long id) {
        User user = adminRepository.findById(id).orElseThrow(()-> new CustomAppException("User doesn't exist!"));

        return user;
    }

//    @Override
//    public void deleteUser(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new CustomAppException("User does not exist."));
//
//        userRepository.delete(user);
//    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("Product does not exist."));

        productRepository.delete(product);
    }

//    @Override
//    public void removeFromWishList(Long id, String email) {
//        WishListItem wishListItem = wishListRepository.findByEmailAndId(email, id)
//                .orElseThrow(() -> new CustomAppException("Product does not exist."));
//
//        wishListRepository.delete(wishListItem);
//    }

//    @Override
//    public void removeFromCart(Long id, String email) {
//        CartItem cartItem = cartRepository.findByEmailAndId(email, id)
//                .orElseThrow(() -> new CustomAppException("Product does not exist."));
//
//        cartRepository.delete(cartItem);
//    }

//    @Override
//    public User updateUser(UpdateUserDto updateUserDto, String email) {
//        User user = adminRepository.findByEmail(email)
//                .orElseThrow(()-> new CustomAppException("User doesn't exit."));
//
//        user.setUsername(updateUserDto.getUsername());
//        user.setEmail(updateUserDto.getEmail());
//        user.setPassword(updateUserDto.getPassword());
//        user.setFirstName(updateUserDto.getFirstName());
//        user.setLastName(updateUserDto.getLastName());
//        user.setAge(updateUserDto.getAge());
//
//        return adminRepository.save(user);
//    }

//    @Override
//    public User login(String email, String password) {
//        User user = adminRepository.findByEmailAndPassword(email, password)
//                .orElseThrow(()-> new CustomAppException("User doesn't exit."));
//
//        return user;
//    }
}
