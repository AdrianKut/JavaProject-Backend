package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("/user/list")
    public String getAll(Model model){
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        return "views/user/list";
    }

    @GetMapping("/user/add")
    public String save(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "views/user/add";
    }

    @PostMapping("/user/add")
    public String save(User user){
        userService.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/edit/{email}")
    public String edit(@PathVariable(name = "email")String email, Model model){
        User user = userService.get(email);
        model.addAttribute("user", user);
        return "views/user/edit";
    }

    @PostMapping("/user/edit/{email}")
    public String edit(@PathVariable(name = "email")String email,
    User user){
        user.setEmail(email);
        userService.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{email}")
    public String delete(@PathVariable(name = "email")String email){
        userService.delete(email);
        return "redirect:/user/list";
    }
}