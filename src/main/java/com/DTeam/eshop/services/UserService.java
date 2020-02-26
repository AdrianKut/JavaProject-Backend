package com.DTeam.eshop.services;

import java.util.List;

import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User get(String name){
        return userRepository.findById(name).get();
    }

    public void delete(String name){
        userRepository.deleteById(name);
    }
}