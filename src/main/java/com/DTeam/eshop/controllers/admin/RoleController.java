package com.DTeam.eshop.controllers.admin;

import java.util.List;

import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoleController {

    @Autowired private RoleService roleService;

    @GetMapping("/admin/role/list")
    public String getAll(Model model){
        List<Role> roleList = roleService.listAll();
        model.addAttribute("roleList", roleList);
        return "views/admin/role/list";
    }

    @GetMapping("/admin/role/add")
    public String save(Model model){
        Role role = new Role();
        model.addAttribute("role", role);
        return "views/admin/role/add";
    }

    @PostMapping("/admin/role/add")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/admin/role/list";
    }
}