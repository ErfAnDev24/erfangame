package com.example.erfangame.modules.roles.model;

import com.example.erfangame.enums.Authorities;
import com.example.erfangame.modules.users.model.Users;

import javax.persistence.*;
import java.util.List;

@Entity
public class Roles {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Users> usersList;

    @ElementCollection(targetClass = Authorities.class , fetch = FetchType.EAGER)
    private List<Authorities> authorities;


    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
