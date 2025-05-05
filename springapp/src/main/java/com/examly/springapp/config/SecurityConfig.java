package com.examly.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class for managing authentication and authorization.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint entryPoint;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Constructor injection for dependencies.
     * @param entryPoint Handles authentication errors.
     * @param userDetailsService Loads user details.
     * @param encoder Encrypts passwords.
     * @param jwtAuthenticationFilter JWT security filter.
     */
    public SecurityConfig(JwtAuthenticationEntryPoint entryPoint, UserDetailsService userDetailsService, 
                          PasswordEncoder encoder, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.entryPoint = entryPoint;
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configures the authentication manager.
     * @param http Security configuration object.
     * @return AuthenticationManager instance.
     * @throws Exception If authentication fails.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    /**
     * Configures security filter chain with authorization rules.
     * @param http Security configuration object.
     * @return Configured security filter chain.
     * @throws Exception If security config fails.
     */
    @Bean
    public SecurityFilterChain cFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/api/register", "/api/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/investments").hasRole("Admin")
                .requestMatchers(HttpMethod.GET, "/api/investments").hasAnyRole("Admin", "User")
                .requestMatchers(HttpMethod.GET, "/api/feedback").hasAnyRole("Admin", "User")
                .requestMatchers(HttpMethod.PUT, "/api/inquiries/{inquiryId}", "/api/investments/{investmentId}").hasRole("Admin")
                .requestMatchers(HttpMethod.GET, "/api/inquiries/{inquiryId}", "/api/inquiries").hasAnyRole("Admin", "User")
                .requestMatchers(HttpMethod.DELETE, "/api/investments/{investmentId}", "/api/feedback/{feedbackId}", "/api/inquiries/{inquiryId}").hasRole("Admin")
                .requestMatchers(HttpMethod.POST, "/api/inquiries", "/api/feedback").hasRole("User")
                .requestMatchers(HttpMethod.GET, "/api/inquiries/user/{userId}", "/api/feedback/user/{userId}", "/api/feedback/{feedbackId}").hasRole("User")
                .requestMatchers(HttpMethod.DELETE, "/api/inquiries/{inquiryId}", "/api/feedback/{feedbackId}").hasRole("User")
                .anyRequest().permitAll())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(entryPoint))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}