package com.DTeam.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Klasa obejmująca logowanie
 * użytkownika
 * @author 
 */
@Controller
public class LoginController {

    /**
     * Metoda obsługująca logowanie
     * @return zwraca widok logowania
     */
    @GetMapping(value = "/login")
    public String login(){
        return "views/login";
    }
}