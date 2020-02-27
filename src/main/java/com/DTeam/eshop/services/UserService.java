package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User get(String email){
        return userRepository.findById(email).get();
    }

    public void delete(String email){
        userRepository.deleteById(email);
    }

    public Boolean isUserExist(String email){
        return userRepository.existsById(email);
    }
}