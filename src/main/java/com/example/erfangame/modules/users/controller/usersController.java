package com.example.erfangame.modules.users.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class usersController {

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String showUsers(){
        return "users/users";
    }

    @RequestMapping(value = "/register")
    public String registerUser(){
        return "users/registerUser";
    }
}
