package com.example.erfangame.modules.roles.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String showMakeRoles(){
        return ""
    }
}
