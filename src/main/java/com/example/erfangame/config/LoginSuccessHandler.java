package com.example.erfangame.config;

import com.example.erfangame.enums.Authorities;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    DefaultRedirectStrategy strategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication.getAuthorities().contains(Authorities.USER)){
            strategy.sendRedirect(request,response,"/users");
        }
        else if (authentication.getAuthorities().contains(Authorities.CATEGORY)){
            strategy.sendRedirect(request,response,"/categories");
        }

        else if (authentication.getAuthorities().contains(Authorities.POST)){
            strategy.sendRedirect(request,response,"/posts");
        }
        else
            strategy.sendRedirect(request,response,"/");
    }
}
