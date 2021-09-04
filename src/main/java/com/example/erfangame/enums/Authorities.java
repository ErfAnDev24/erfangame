package com.example.erfangame.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authorities implements GrantedAuthority {

    USER,
    POST,
    CATEGORY,
    ROLES,
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
