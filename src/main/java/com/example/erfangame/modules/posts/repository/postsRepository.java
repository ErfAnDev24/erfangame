package com.example.erfangame.modules.posts.repository;

import com.example.erfangame.modules.posts.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postsRepository extends JpaRepository<Posts,Long> {
}
