package com.DTeam.eshop.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import com.DTeam.eshop.entities.User;
import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.services.UserService;
import com.DTeam.eshop.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;

    @GetMapping("/admin/user/list")
    public String getAll(Model model){
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        return "views/admin/user/list";
    }

    @GetMapping("/admin/user/add")
    public String save(Model model){
        List<Role> roleList = roleService.listAll();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "views/admin/user/add";
    }

    @PostMapping("/admin/user/add")
    public String save(User user,
     @RequestParam(name="roles",required = false)ArrayList<Role> roles){
        userService.save(user);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/admin/user/edit/{email}")
    public String edit(@PathVariable(name = "email")String email, Model model){
        List<Role> roleList = roleService.listAll();
        User user = userService.get(email);
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "views/admin/user/edit";
    }

    @PostMapping("/admin/user/edit/{email}")
    public String edit(@PathVariable(name = "email")String email,
    User user, @RequestParam(name="roles",required = false)ArrayList<Role> roles){
        User currentUser = userService.get(email);
        currentUser.setEnabled(user.getEnabled());
        currentUser.setPassword(user.getPassword());
        currentUser.setRoles(roles);
        userService.save(currentUser);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/admin/user/delete/{email}")
    public String delete(@PathVariable(name = "email")String email){
        userService.delete(email);
        return "redirect:/admin/user/list";
    }
}