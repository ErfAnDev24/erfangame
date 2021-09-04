package com.example.erfangame.modules.users.controller;


import com.example.erfangame.modules.roles.service.RoleService;
import com.example.erfangame.modules.users.model.Users;
import com.example.erfangame.modules.users.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class usersController {

    private userService userService;
    private RoleService roleService;

    @Autowired
    public usersController(com.example.erfangame.modules.users.service.userService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String showUsers(Model model,@ModelAttribute("u") Users users
            ,@PageableDefault(size = 5) Pageable pageable){
        model.addAttribute("users",userService.findBySearch(users,pageable));
        return "users/users";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String ShowRegisterUser(Model model){
        model.addAttribute("roles" , roleService.findAllRoles());
        model.addAttribute("user" , new Users());
        return "users/registerUser";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") @Valid Users users, BindingResult bindingResult, Model model) throws IOException, InvocationTargetException, IllegalAccessException {
        if(bindingResult.hasErrors()){
            model.addAttribute("roles" , roleService.findAllRoles());
            return "users/registerUser";
        }
         this.userService.registerUser(users);
         return "redirect:/users";
    }

    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
    public String ShowEditUser(Model model , @PathVariable("id") Long id){
        model.addAttribute("roles" , roleService.findAllRoles());
        model.addAttribute("user" , userService.findUserById(id));
        return "users/registerUser";
    }

    @RequestMapping(value = "/profile" , method = RequestMethod.GET)
    public String showProfile(Model model, Principal principal){
        model.addAttribute("user" , userService.findByEmail(principal.getName()));
        return "users/showUser";
    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    @PreAuthorize("#id!=authentication.principal.id")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }
}
