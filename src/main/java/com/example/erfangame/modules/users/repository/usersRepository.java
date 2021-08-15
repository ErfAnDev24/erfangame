package com.example.erfangame.modules.users.repository;

import com.example.erfangame.modules.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<Users,Long> {
}
