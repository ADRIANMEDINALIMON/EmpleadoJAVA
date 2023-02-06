package com.adrian.proyecto.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration

public class SecurityConfig {

    @Bean

    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withUsername("admin")
                .password("{noop}123")
                .roles("ADMIN", "USER")
                .build();

        UserDetails user2 = User.withUsername("user")
                .password("{noop}123")
                .roles("USER")
                .build();        
        return new InMemoryUserDetailsManager(user,user2);
    }
    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                    .hasRole("ADMIN")
                .antMatchers("/")
                    .hasAnyRole("USER", "ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/errores/403");

        return http.build();

    }
   
}
