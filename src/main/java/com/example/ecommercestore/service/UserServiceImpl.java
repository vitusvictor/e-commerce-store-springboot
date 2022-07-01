package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.UserDto;
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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    public UserServiceImpl(AdminRepository adminRepository, ProductRepository productRepository) {
        this.adminRepository = adminRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User create(UserDto userDto) {
        Optional<User> dbUser = adminRepository.findByEmail(userDto.getEmail());
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

        return adminRepository.save(user);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public WishListItem addToWishList(Long id, String email) {
        Product product1 = productRepository.findById(id)
                        .orElseThrow(()-> new CustomAppException("Product not found!"));

        WishListItem wishListItem = new WishListItem();

        wishListItem.setProductName(product1.getProductName());
        wishListItem.setCategory(product1.getCategory());
        wishListItem.setPrice(product1.getPrice());
        wishListItem.setEmail(email);

        return wishListRepository.save(wishListItem);
    }

    @Override
    public CartItem addToCart(Long id, String email) {
        Product product1 = productRepository.findById(id)
                .orElseThrow(()-> new CustomAppException("Product not found!"));

        CartItem cartItem = new CartItem();
        System.out.println("printing from addToCart user implementation" + email);
        cartItem.setProductName(product1.getProductName());
        cartItem.setCategory(product1.getCategory());
        cartItem.setPrice(product1.getPrice());
        cartItem.setEmail(email);

        return cartRepository.save(cartItem);
    }

    @Override
    public List<WishListItem> getWishList(String email) {
        return wishListRepository.findByEmail(email).orElseThrow(()-> new CustomAppException("Nothing found!"));
    }

    @Override
    public List<CartItem> getCart(String email) {
        return  cartRepository.findByEmail(email).orElseThrow(()-> new CustomAppException("Nothing found!"));
    }

    @Override
    public void removeFromWishList(Long id, String email) {
        WishListItem wishListItem = wishListRepository.findByEmailAndId(email, id)
                .orElseThrow(() -> new CustomAppException("Product does not exist."));

        wishListRepository.delete(wishListItem);
    }

    @Override
    public void removeFromCart(Long id, String email) {
        CartItem cartItem = cartRepository.findByEmailAndId(email, id)
                .orElseThrow(() -> new CustomAppException("Product does not exist."));

        cartRepository.delete(cartItem);
    }

    @Override
    public User login(String email, String password) {
        User user = adminRepository.findByEmailAndPassword(email, password)
                .orElseThrow(()-> new CustomAppException("User doesn't exit."));

        return user;
    }
}
