package com.api.school.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/books/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/loans/return/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/books/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/loans/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/loans/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/loans/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/loans/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
