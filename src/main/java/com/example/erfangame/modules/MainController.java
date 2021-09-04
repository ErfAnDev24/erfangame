package com.example.erfangame.modules;

import com.example.erfangame.modules.categories.service.categoryService;
import com.example.erfangame.modules.posts.model.Posts;
import com.example.erfangame.modules.posts.service.postService;
import com.example.erfangame.modules.users.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
public class MainController {


    private postService postService;
    private categoryService categoryService;
    private userService userService;

    @Autowired
    public MainController(com.example.erfangame.modules.posts.service.postService postService, com.example.erfangame.modules.categories.service.categoryService categoryService, com.example.erfangame.modules.users.service.userService userService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Model model, @ModelAttribute("p") Posts posts, @PageableDefault(size = 6) Pageable pageable) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("posts", postService.findBySearch(posts, pageable));
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }

    @GetMapping("/info")
    public @ResponseBody
    Principal getInfo(Principal principal) {
        return principal;
    }
}