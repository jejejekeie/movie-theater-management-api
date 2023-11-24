package com.cev.prueba.prueba.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(@org.jetbrains.annotations.NotNull HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/pelicula/**").permitAll()
                        .requestMatchers("/api/peliculas/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/cines/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/reviews/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/reviews").hasRole("ADMIN")
                        .requestMatchers("/api/peliculas").hasRole("ADMIN")
                        .requestMatchers("/api/cines").hasRole("ADMIN")
                        .requestMatchers("/api/cines/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/peliculas/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/reviews/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/no-csrf", "/api/peliculas/**","/api/cines/**", "/api/reviews/**", "/api/pelicula/**"))
                .headers((headers) -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin
                        )
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("ADMIN")
                .password(passwordEncoder().encode("ADMIN"))
                .authorities("ROLE_ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("USER")
                .password(passwordEncoder().encode("USER"))
                .authorities("ROLE_USER")
                .build();
    return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
