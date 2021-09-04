package com.example.erfangame.modules.users.model;

import com.example.erfangame.enums.Authorities;
import com.example.erfangame.modules.posts.model.Posts;
import com.example.erfangame.modules.roles.model.Roles;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Users implements Serializable, UserDetails , OAuth2User {

    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "* please choose a name for your user")
    private String name;

    @NotBlank(message = "* please insert an email for your user")
    private String email;

    @Transient
    private String noProfile="NoProfile.png";

    private String cover;

    @NotBlank(message = "* please insert a password for your user")
    private String password;

    @Transient
    private MultipartFile file;


    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "email" , referencedColumnName = "email"))
    @NotEmpty(message = "* please select a role for your user")
    private List<Roles> roles;


    @OneToMany(mappedBy = "users")
    private List<Posts> posts;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getNoProfile() {
        return noProfile;
    }

    public void setNoProfile(String noProfile) {
        this.noProfile = noProfile;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return new HashMap<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(roles !=null && roles.isEmpty() == false){
            for (Roles roles : roles) {
                authorities.addAll(roles.getAuthorities());
            }
        }else{
            authorities.add(Authorities.POST);
        }

        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }


}
