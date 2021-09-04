package com.example.erfangame.config;

import com.example.erfangame.enums.Authorities;
import com.example.erfangame.modules.users.model.Users;
import com.example.erfangame.modules.users.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class OAuth2UserService extends DefaultOAuth2UserService {

    private usersRepository usersRepository;

    @Autowired
    public OAuth2UserService(com.example.erfangame.modules.users.repository.usersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Users users = usersRepository.findByEmail(oAuth2User.getAttribute("email"));

        if(users == null){
            users = new Users();
            users.setEmail(oAuth2User.getAttribute("email"));
        }

        users.setName(oAuth2User.getAttribute("name"));
        users = usersRepository.save(users);

        return users;
    }
}
