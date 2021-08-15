package com.example.erfangame.modules.roles.model;

import com.example.erfangame.modules.users.model.Users;

import javax.persistence.*;
import java.util.List;

@Entity
public class Roles {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @ManyToMany
    private List<Users> usersList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
