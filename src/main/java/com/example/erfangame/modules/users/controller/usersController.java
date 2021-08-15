package com.example.erfangame.modules.users.controller;


import com.example.erfangame.modules.users.model.Users;
import com.example.erfangame.modules.users.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class usersController {

    private userService userService;

    @Autowired
    public usersController(com.example.erfangame.modules.users.service.userService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String showUsers(Model model){
        model.addAttribute("users",userService.findAllUsers());
        return "users/users";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String ShowRegisterUser(Model model){
        model.addAttribute("user" , new Users());
        return "users/registerUser";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerUser(@ModelAttribute Users users){
         this.userService.registerUser(users);
         return "redirect:/users";
    }

    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
    public String ShowEditUser(Model model , @PathVariable("id") Long id){
        model.addAttribute("user" , userService.findUserById(id));
        return "users/registerUser";
    }

    @RequestMapping(value = "/profile" , method = RequestMethod.GET)
    public String showProfile(){
        return "users/showUser";
    }
}
