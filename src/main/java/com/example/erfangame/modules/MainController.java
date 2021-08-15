package com.example.erfangame.modules;

import com.example.erfangame.modules.posts.service.postService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    private postService postService;


    @Autowired
    public MainController(com.example.erfangame.modules.posts.service.postService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String showIndex(Model model ,@PageableDefault(size = 5) Pageable pageable)
    {
        model.addAttribute("posts",postService.findPosts(pageable));
        return "index";
    }
}
