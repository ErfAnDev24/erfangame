package com.example.erfangame.modules.posts.controller;

import com.example.erfangame.modules.categories.service.categoryService;
import com.example.erfangame.modules.posts.model.Posts;
import com.example.erfangame.modules.posts.service.postService;
import com.example.erfangame.modules.users.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/posts")
public class postsController {

    private postService postService;
    private categoryService categoryService;
    private userService userService;


    @Autowired
    public postsController(com.example.erfangame.modules.posts.service.postService postService, com.example.erfangame.modules.categories.service.categoryService categoryService, com.example.erfangame.modules.users.service.userService userService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.userService = userService;
    }


    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String ShowRegisterPost(Model model){
        model.addAttribute("categories" , categoryService.findAllCategories());
        model.addAttribute("post" , new Posts());
        return "/posts/registerPost";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerPost(@ModelAttribute(name = "post") @Valid Posts posts, BindingResult bindingResult, Model model, Principal principal) throws IOException, InvocationTargetException, IllegalAccessException {
        if(bindingResult.hasErrors()){
            model.addAttribute("categories" , categoryService.findAllCategories());
            return "/posts/registerPost";
        }
        posts.setUsers(userService.findByEmail(principal.getName()));
        postService.registerPost(posts);
        return "redirect:/posts";
    }


    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
    public String ShowEdit(Model model , @PathVariable("id") Long id){
        model.addAttribute("categories" , categoryService.findAllCategories());
        model.addAttribute("post" , postService.findPostById(id));
        return "/posts/registerPost";
    }

    @RequestMapping(value = "/showPost/{id}" , method = RequestMethod.GET)
    public String showPost(@PathVariable("id") Long id , Model model){
        model.addAttribute("post" , postService.findPostById(id));
        return "posts/showPost";
    }


    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") Long id){
        postService.deleteById(id);
        return "redirect:/posts";
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String findBySearch(Model model,@ModelAttribute("p") Posts posts,@PageableDefault(size = 5) Pageable pageable){
        model.addAttribute("categories" , categoryService.findAllCategories());
        model.addAttribute("posts",postService.findBySearch(posts,pageable));
        return "/posts/posts";
    }
}
