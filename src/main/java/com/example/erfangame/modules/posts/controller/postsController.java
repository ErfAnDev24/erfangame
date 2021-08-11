package com.example.erfangame.modules.posts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class postsController {

    @RequestMapping("")
    public String posts(){
        return "/posts/posts";
    }
}
