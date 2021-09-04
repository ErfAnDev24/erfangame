package com.example.erfangame.modules.categories.controller;

import com.example.erfangame.modules.categories.model.Category;
import com.example.erfangame.modules.categories.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/categories")
public class categoryCotroller {


    private categoryService categoryService;

    @Autowired
    public categoryCotroller(com.example.erfangame.modules.categories.service.categoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String categories(Model model)
    {
        model.addAttribute("categories" , categoryService.findAllCategories());
        return "categories/categories";

    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String ShowRegisterCategory(Model model){
        model.addAttribute("category" , new Category());
        return "categories/registerCategory";
    }

    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
    public String showEditCategory(Model model , @PathVariable("id") Long id){
        model.addAttribute("category" , categoryService.findCategoryById(id));
        return "categories/registerCategory";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerCategory(@ModelAttribute Category category){
        categoryService.registerCategory(category);
        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }


}
