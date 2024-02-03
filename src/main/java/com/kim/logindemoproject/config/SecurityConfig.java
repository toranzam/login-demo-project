package com.kim.logindemoproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public PasswordEncoder passwordEncoder2() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                request -> request
                        .antMatchers("/signUp", "/login", "/loginForm", "h2-console/**").permitAll()
                        .anyRequest().authenticated()

        );
        http.formLogin(form -> form.loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
        );

        http.headers(header -> header.frameOptions().disable());

        http.csrf().disable();

        return http.build();
    }
}
