package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User get(String email) {
        return userRepository.findById(email).get();
    }

    public User delete(String email) {
        User user = get(email);
        userRepository.deleteById(email);
        return user;
    }

    public Boolean isUserExist(String email) {
        return userRepository.existsById(email);
    }
}
