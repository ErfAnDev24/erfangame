package com.example.erfangame.modules.roles.controller;


import com.example.erfangame.enums.Authorities;
import com.example.erfangame.modules.roles.model.Roles;
import com.example.erfangame.modules.roles.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String showMakeRoles(Model model){
        model.addAttribute("roles" , roleService.findAllRoles());
        return "roles/makeRoles";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String showRegisterRoles(Model model){
        model.addAttribute("role" , new Roles());
        return "roles/registerRoles";
    }

    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
    public String showEditRoles(Model model, @PathVariable("id") Long id){
        model.addAttribute("role" , roleService.findRoleById(id));
        return "roles/registerRoles";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerRoles(@ModelAttribute Roles roles){
        roleService.registerRole(roles);
        return "redirect:/roles";
    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteRole(@PathVariable("id") Long id){
        roleService.deleteRole(id);
        return "redirect:/roles";
    }
}
