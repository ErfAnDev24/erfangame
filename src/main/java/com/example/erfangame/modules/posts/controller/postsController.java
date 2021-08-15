package com.example.erfangame.modules.posts.controller;

import com.example.erfangame.modules.categories.service.categoryService;
import com.example.erfangame.modules.posts.model.Posts;
import com.example.erfangame.modules.posts.service.postService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/posts")
public class postsController {

    private postService postService;
    private categoryService categoryService;

    @Autowired
    public postsController(com.example.erfangame.modules.posts.service.postService postService, com.example.erfangame.modules.categories.service.categoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String posts(Model model ,@PageableDefault(size = 5) Pageable pageable)
    {
        model.addAttribute("posts",postService.findPosts(pageable));
        return "/posts/posts";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String ShowRegisterPost(Model model){
        model.addAttribute("categories" , categoryService.findAllCategories());
        model.addAttribute("post" , new Posts());
        return "/posts/registerPost";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerPost(@ModelAttribute Posts posts) throws IOException {
        postService.registerPost(posts);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
    public String showEditPost(Model model , @PathVariable("id") Long id){
        model.addAttribute("post" , this.postService.findPostById(id));
        return "/posts/registerPost";
    }

    @RequestMapping(value = "/showPost" , method = RequestMethod.GET)
    public String showPost(){
        return "posts/showPost";
    }

}
