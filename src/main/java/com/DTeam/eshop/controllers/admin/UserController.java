package com.DTeam.eshop.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.services.UserService;
import com.DTeam.eshop.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  Klasa obsługująca Użytkowników
 * @author 
 */
@Controller
public class UserController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;

    /**
     * Metoda wyświetlająca liste Użytkowników
     * @param model przechowywanie atrybutów modelu
     * @return widok listy Użytkowników
     */
    @GetMapping("/admin/user/list")
    public String getAll(Model model){
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        return "views/admin/user/list";
    }

    /**
     * Metoda dodająca Użytkownika
     * @param model przechowywanie atrybutów modelu
     * @return widok formularza Użytkownika
     */
    @GetMapping("/admin/user/add")
    public String save(Model model){
        List<Role> roleList = roleService.listAll();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "views/admin/user/add";
    }

    /**
     * Metoda dodająca Użytkownika
     * @param user przechowuje dane Użytkownika
     * @param bindingResult walidacja błędów
     * @param roles przechowuje Dane Role
     * @return widok listy Użytkowników
     */
    @PostMapping("/admin/user/add")
    public String save(@Valid User user, BindingResult bindingResult,
     @RequestParam(name="roles",required = false)ArrayList<Role> roles){
        if(bindingResult.hasErrors()){
            return "views/admin/user/add";
        }
        userService.save(user);
        return "redirect:/admin/user/list";
    }

    /**
     * Metoda educji Użytkownika
     * @param email przechowuje emali Użytkownika
     * @param model przechowywanie atrybutów modelu
     * @return widok edycji folmularza Uzytkownika
     */
    @GetMapping("/admin/user/edit/{email}")
    public String edit(@PathVariable(name = "email")String email, Model model){
        List<Role> roleList = roleService.listAll();
        User user = userService.get(email);
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "views/admin/user/edit";
    }

    /**
     * Metoda educji Użytkownika
     * @param email przechowuje e-mail Uzytkownika
     * @param user przechowuje Dane Uzytkownika
     * @param bindingResult wlaidacja błędów
     * @param roles przechowuje role Użytkownika
     * @return widok listy Użytkowników
     */
    @PostMapping("/admin/user/edit/{email}")
    public String edit(@PathVariable(name = "email")String email,
    @Valid User user, BindingResult bindingResult, @RequestParam(name="roles",required = false)ArrayList<Role> roles){
        if(bindingResult.hasErrors()){
            return "views/admin/user/edit";
        }
        User currentUser = userService.get(email);
        currentUser.setEnabled(user.getEnabled());
        currentUser.setPassword(user.getPassword());
        currentUser.setRoles(roles);
        userService.save(currentUser);
        return "redirect:/admin/user/list";
    }

    /**
     * Metoda usuń Użytkwnika
     * @param email rzechowuje email Użytkownika
     * @return widok listy Użytkowników
     */
    @GetMapping("/admin/user/delete/{email}")
    public String delete(@PathVariable(name = "email")String email){
        userService.delete(email);
        return "redirect:/admin/user/list";
    }
}