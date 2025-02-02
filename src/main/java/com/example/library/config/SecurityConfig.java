package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.builder()
        .username("admin")
        .password(passwordEncoder().encode("password"))
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        // Request Authorization Rules
        .authorizeHttpRequests(auth -> auth
            // Public endpoints
            .requestMatchers("/").permitAll()
            .requestMatchers("/api/public/**").permitAll()
            .requestMatchers("/actuator/health").permitAll()
            .requestMatchers("/swagger-ui/**").permitAll()
            // Static resources
            .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.ico").permitAll()
            // Admin endpoints
            .requestMatchers("/admin/**").hasRole("ADMIN")
            // API endpoints
            .requestMatchers("/api/**").authenticated()
            // Any other request
            .anyRequest().authenticated())
        .formLogin(form -> form
            .permitAll())
        .httpBasic(basic -> basic.disable());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}