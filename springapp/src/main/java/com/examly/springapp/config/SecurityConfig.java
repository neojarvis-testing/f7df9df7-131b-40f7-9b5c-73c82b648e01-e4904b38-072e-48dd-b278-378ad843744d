package com.examly.springapp.config;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthenticationEntryPoint entryPoint;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    public void configure(AuthenticationManagerBuilder authority)throws Exception{
        authority.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder)
        .and()
        .build();
    }
    @Bean
    public SecurityFilterChain cFilterChain(HttpSecurity http)throws Exception{
         http.cors(cors->cors.disable())
        .csrf(csrf->csrf.disable())
        .authorizeHttpRequests(auth->auth
        .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
        .requestMatchers("/api/register","/api/login").permitAll()
        .requestMatchers(HttpMethod.POST,"/api/investments").hasRole("Admin")
        .requestMatchers(HttpMethod.GET,"/api/investments").hasAnyRole("Admin","User")
        .requestMatchers(HttpMethod.GET,"/api/inquiries","api/feedback").hasRole("Admin")
        .requestMatchers(HttpMethod.PUT,"/api/inquiries/{inquiryId}","/api/investments/{investmentId}").hasRole("Admin")
        .requestMatchers(HttpMethod.DELETE,"/api/investments/{investmentId}").hasRole("Admin")
        .requestMatchers(HttpMethod.GET,"/api/inquiries/{inquiryId}","/api/feedback/{feedbackId}").hasAnyRole("Admin","User")
        .requestMatchers(HttpMethod.POST,"/api/inquiries","/api/feedback").hasRole("User")
        .requestMatchers(HttpMethod.GET,"/api/inquiries/user/{userId}","/api/feedback/user/{userId}").hasRole("User")
        .requestMatchers(HttpMethod.DELETE,"/api/inquiries/{inquiryId}","/api/feedback/{feedbackId}").hasRole("User")
        .anyRequest().permitAll())
        .exceptionHandling(exception->exception.authenticationEntryPoint(entryPoint))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}