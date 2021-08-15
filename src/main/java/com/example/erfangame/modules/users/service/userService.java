package com.example.erfangame.modules.users.service;

import com.example.erfangame.modules.users.model.Users;
import com.example.erfangame.modules.users.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class userService {

    private usersRepository usersRepository;

    @Autowired
    public userService(com.example.erfangame.modules.users.repository.usersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAllUsers() {
        return this.usersRepository.findAll();
    }

    @Transactional
    public void registerUser(Users users) {
        usersRepository.save(users);
    }

    public Optional<Users> findUserById(Long id) {
        return usersRepository.findById(id);
    }
}
