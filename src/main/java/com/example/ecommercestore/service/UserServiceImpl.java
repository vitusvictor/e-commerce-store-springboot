package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.UpdateUserDto;
import com.example.ecommercestore.dto.UserRegisterDto;
import com.example.ecommercestore.exception.CustomAppException;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserRegisterDto userRegisterDto) {
        Optional<User> dbUser = userRepository.findByEmail(userRegisterDto.getEmail());
        if (dbUser.isPresent()) {
            throw new CustomAppException("User already exists.");
        }

        User user = new User();

        user.setUsername(userRegisterDto.getUsername());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setAge(userRegisterDto.getAge());

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
