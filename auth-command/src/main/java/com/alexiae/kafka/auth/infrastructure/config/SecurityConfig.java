package com.alexiae.kafka.auth.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/h2-console",
            "/h2-console/**",
            "/auth/login",
            "/auth/register",
    };

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliza BCrypt para hashear contraseñas
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(
                        AbstractHttpConfigurer::disable) // Disabling CSRF as we use JWT which is immune to CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URL)
                        .permitAll() // Whitelisting some paths from authentication
                        .anyRequest().authenticated()) // All other requests must be authenticated
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session management
                //.authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class); // Registering our JwtAuthFilter
//        http
//                .csrf(
//                        AbstractHttpConfigurer::disable) // Disabling CSRF as we use JWT which is immune to CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(WHITE_LIST_URL)
//                        .permitAll() // Whitelisting some paths from authentication
//                        .anyRequest().authenticated()) // All other requests must be authenticated
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Stateless session management


        return http.build(); // Construir el filtro de seguridad
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
