package com.example.erfangame.modules.categories.repository;

import com.example.erfangame.modules.categories.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<Category,Long> {
}
