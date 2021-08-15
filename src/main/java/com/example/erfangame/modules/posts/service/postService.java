package com.example.erfangame.modules.posts.service;

import com.example.erfangame.modules.posts.model.Posts;
import com.example.erfangame.modules.posts.repository.postsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class postService {


    private postsRepository postsRepository;

    @Autowired
    public postService(com.example.erfangame.modules.posts.repository.postsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public Page<Posts> findPosts(Pageable pageable){
        return postsRepository.findAll(pageable);
    }

    @Transactional
    public void registerPost(Posts posts) throws IOException {

        String path = ResourceUtils.
                getFile("classpath:static/img").getAbsolutePath();
        String name = UUID.randomUUID() + "." + Objects.requireNonNull
                (posts.getFile().getContentType().split("/")[1]);
        byte bytes [] = posts.getFile().getBytes();
        Files.write(Paths.get(path + File.separator + name),bytes);
        posts.setCover(name);

        postsRepository.save(posts);
    }

    public Optional<Posts> findPostById(Long id) {
        return postsRepository.findById(id);
    }
}
