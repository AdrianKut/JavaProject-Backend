package com.DTeam.eshop.repositories;

import com.DTeam.eshop.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{

}