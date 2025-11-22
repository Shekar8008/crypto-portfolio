package com.guna.newproject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class Securityconfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF: disable only for H2 console path to keep other endpoints protected
        http.csrf(csrf -> csrf.disable());

        // Allow H2 console frames
        http.headers(headers -> headers .frameOptions(frame -> frame.sameOrigin())
        );

        http
            .authorizeHttpRequests(auth -> auth
                // allow H2 console if present
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                // allow static resources and login page
                .requestMatchers(new AntPathRequestMatcher("/login.html")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                // all other requests require authentication
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.html")          // static login page
                .loginProcessingUrl("/login")      // form action URL
                .defaultSuccessUrl("/index.html", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login.html?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("guna")
            .password("123")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
