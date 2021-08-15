package com.example.erfangame.modules.categories.service;

import com.example.erfangame.modules.categories.model.Category;
import com.example.erfangame.modules.categories.repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class categoryService {

    private categoryRepository categoryRepository;

    @Autowired
    public categoryService(com.example.erfangame.modules.categories.repository.categoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public void registerCategory(Category category) {
         categoryRepository.save(category);
    }
}
