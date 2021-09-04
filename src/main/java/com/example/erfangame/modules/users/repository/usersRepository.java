package com.example.erfangame.modules.users.repository;

import com.example.erfangame.modules.users.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface usersRepository extends JpaRepository<Users,Long> {
    
    Users findByEmail(String email);

    @Query("select u from Users u where u.email like concat('%',:#{#users.email},'%') or :#{#users.email} is null")
    Page<Users> finbBySearch(Users users, Pageable pageable);
}
