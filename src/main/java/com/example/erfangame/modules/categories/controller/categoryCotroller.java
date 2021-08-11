package com.example.erfangame.modules.categories.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/categories")
public class categoryCotroller {


    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String categories(){
        return "category/categories";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String registerCategory(){
        return "category/registerCategory";
    }
}
