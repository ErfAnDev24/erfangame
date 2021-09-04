package com.example.erfangame.modules.posts.repository;

import com.example.erfangame.modules.posts.model.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface postsRepository extends JpaRepository<Posts,Long> {

    @Query("select p from Posts p join p.categoryList pc where ((:#{#posts.title} is null) or (p.title like concat('%',:#{#posts.title},'%')))" +
            "and ((coalesce(:#{#posts.categoryList},null) is null) or (pc in (:#{#posts.categoryList})))" +
            "group by p.id having count(p.id)>=:num")
    Page<Posts> findBySearch(Posts posts, Pageable pageable, @Param("num") Long size);
}
