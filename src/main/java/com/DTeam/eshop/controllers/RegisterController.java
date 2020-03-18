package com.DTeam.eshop.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.services.RoleService;
import com.DTeam.eshop.services.UserService;
import com.DTeam.eshop.utilities.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/register")
    public String register(Model model){
     User user = new User();
     model.addAttribute("user", user);
     return "views/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult,
            Model model){
        if(bindingResult.hasErrors()){
              return "views/register";
        }
        if(userService.isUserExist(user.getEmail())){
            model.addAttribute("exist", true);
             return "views/register";
        }

        Role role  = roleService.get("klient");
        List<Role> roleList = new ArrayList<Role>();
        roleList.add(role);
        user.setRoles(roleList);
        userService.save(user);

        Context context = new Context();
        context.setVariable("header", "Dziękujemy za rejestrację!");
        context.setVariable("title", "Witaj, " + user.getEmail());
        context.setVariable("description", "Dziękujęmy, że zarejestrowałeś się w naszym sklepie. Mamy nadzieję, że znajdziesz tutaj " +
        "produkty, których szukasz. Zapraszamy :)");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail(user.getEmail(), "Rejestracja udana", body);

        model.addAttribute("success", true);
        return "views/register";
    }
}