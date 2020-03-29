package com.DTeam.eshop.controllers.admin;

import java.util.List;

import com.DTeam.eshop.entities.Role;
import com.DTeam.eshop.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Klasa obsługująca Role
 * @author 
 */
@Controller
public class RoleController {

    @Autowired private RoleService roleService;

    /**
     * Metoda wyświetlająca liste Role
     * @param model przechowywanie atrybutów modelu
     * @return widok lisy Role
     */
    @GetMapping("/admin/role/list")
    public String getAll(Model model){
        List<Role> roleList = roleService.listAll();
        model.addAttribute("roleList", roleList);
        return "views/admin/role/list";
    }

    /**
     * Metoda dodająca Role
     * @param model przechowywanie atrybutów modelu
     * @return widok formularza Role
     */
    @GetMapping("/admin/role/add")
    public String save(Model model){
        Role role = new Role();
        model.addAttribute("role", role);
        return "views/admin/role/add";
    }

    /**
     * Metoda dodająca Role
     * @param role przechowuje Dane Role
     * @return widok lisy Role
     */
    @PostMapping("/admin/role/add")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/admin/role/list";
    }

    /**
     * Metoda usuwania Role
     * @param name przechowuje nazwa Role
     * @return widok lisy Role
     */
    @GetMapping("/admin/role/delete/{name}")
    public String delete(@PathVariable(name = "name")String name){
       roleService.delete(name);
        return "redirect:/admin/role/list";
    }
}