package com.example.erfangame.modules.users.service;

import com.example.erfangame.MyBeanCopy;
import com.example.erfangame.modules.users.model.Users;
import com.example.erfangame.modules.users.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class userService implements UserDetailsService {

    private usersRepository usersRepository;

    @Autowired
    public userService(com.example.erfangame.modules.users.repository.usersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Page<Users> findAllUsers(Pageable pageable) {
        return this.usersRepository.findAll(pageable);
    }

    @Transactional
    public Users registerUser(Users users) throws IOException, InvocationTargetException, IllegalAccessException {
        String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
        String name = UUID.randomUUID() + "." + Objects.requireNonNull(users.getFile().getContentType().split("/")[1]);
        byte bytes [] = users.getFile().getBytes();

        if(name==null){
            users.setCover("NoProfile.png");
        }else {
            users.setCover(name);
        }
        Files.write(Paths.get(path + File.separator + name),bytes);

        if (users.getId() !=null){
            Optional<Users> exist = usersRepository.findById(users.getId());
            MyBeanCopy myBeanCopy = new MyBeanCopy();
            myBeanCopy.copyProperties(exist,users);
            users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
            return usersRepository.save(users);
        }

        users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
        return usersRepository.save(users);
    }

    public Optional<Users> findUserById(Long id) {
        return usersRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }



    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Page<Users> findBySearch(Users users, Pageable pageable) {
        return usersRepository.finbBySearch(users,pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return usersRepository.findByEmail(username);
    }
}
