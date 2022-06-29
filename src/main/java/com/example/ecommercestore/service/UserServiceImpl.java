package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.dto.UpdateUserDto;
import com.example.ecommercestore.dto.UserDto;
import com.example.ecommercestore.exception.CustomAppException;
import com.example.ecommercestore.models.Cart;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.WishList;
import com.example.ecommercestore.repository.CartRepository;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.repository.UserRepository;
import com.example.ecommercestore.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private WishListRepository wishListRepository;
    private CartRepository cartRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User create(UserDto userDto) {
        Optional<User> dbUser = userRepository.findByEmail(userDto.getEmail());
        if (dbUser.isPresent()) {
            throw new CustomAppException("User already exists.");
        }

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
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
    public WishList addToWishList(Long id, String email) {
        Product product1 = productRepository.findById(id)
                        .orElseThrow(()-> new CustomAppException("Product not found!"));

        WishList wishList = new WishList();

        wishList.setProductName(product1.getProductName());
        wishList.setCategory(product1.getCategory());
        wishList.setPrice(product1.getPrice());
        wishList.setEmail(email);

        return wishListRepository.save(wishList);
    }

    @Override
    public Cart addToCart(Long id, String email) {
        Product product1 = productRepository.findById(id)
                .orElseThrow(()-> new CustomAppException("Product not found!"));

        Cart cart = new Cart();

        cart.setProductName(product1.getProductName());
        cart.setCategory(product1.getCategory());
        cart.setPrice(product1.getPrice());
        cart.setEmail(email);

        return cartRepository.save(cart);
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
    public List<WishList> getWishList(String email) {
        return wishListRepository.findByEmail(email).orElseThrow(()-> new CustomAppException("Nothing found!"));
    }

    @Override
    public List<Cart> getCart(String email) {
        return  cartRepository.findByEmail(email).orElseThrow(()-> new CustomAppException("Nothing found!"));
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new CustomAppException("User doesn't exist!"));

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("User does not exist."));

        userRepository.delete(user);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("Product does not exist."));

        productRepository.delete(product);
    }

    @Override
    public void removeFromWishList(Long id, String email) {
        wishListRepository.findByEmailAndId(email, id);
    }

    @Override
    public void removeFromCart(Long id, String email) {
        cartRepository.findByEmailAndId(email, id);
    }

    @Override
    public User updateUser(UpdateUserDto updateUserDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new CustomAppException("User doesn't exit."));

        user.setUsername(updateUserDto.getUsername());
        user.setEmail(updateUserDto.getEmail());
        user.setPassword(updateUserDto.getPassword());
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setAge(updateUserDto.getAge());

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(()-> new CustomAppException("User doesn't exit."));

        return user;
    }
}
