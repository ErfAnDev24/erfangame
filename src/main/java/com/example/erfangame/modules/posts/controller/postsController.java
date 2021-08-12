package com.example.erfangame.modules.posts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/posts")
public class postsController {

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String posts(){
        return "/posts/posts";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String registerPost(){
        return "/posts/registerPost";
    }

    @RequestMapping(value = "/showPost" , method = RequestMethod.GET)
    public String showPost(){
        return "posts/showPost";
    }

}
