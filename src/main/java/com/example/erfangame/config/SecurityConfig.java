package com.example.erfangame.config;


import com.example.erfangame.modules.users.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private OAuth2UserService oAuth2UserService;
    private userService userService;

    @Autowired
    public SecurityConfig(OAuth2UserService oAuth2UserService, com.example.erfangame.modules.users.service.userService userService) {
        this.oAuth2UserService = oAuth2UserService;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/jwt/login/**","/info","/error/**","/","/login/**","/index","/css/**","/js/**","/img/**")
                .permitAll()
                .antMatchers("/users/**").hasAuthority("USER")
                .antMatchers("/posts/**").hasAuthority("POST")
                .antMatchers("/categories/**").hasAuthority("CATEGORY")
                .antMatchers("/roles/**").hasAuthority("ROLES")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").usernameParameter("email")
                .successHandler(new LoginSuccessHandler())
                .and().oauth2Login().loginPage("/oauth2Login").authorizationEndpoint().baseUri("/login/oauth2")
                .and().redirectionEndpoint().baseUri("/login/callback")
                .and().userInfoEndpoint().userService(oAuth2UserService)
                .and()
                .and().exceptionHandling().accessDeniedPage("/error")
                .and().logout().logoutUrl("/myLogout").logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
