package com.examly.springapp.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.examly.springapp.repository.UserRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public SecurityFilterChain createChain(HttpSecurity http) throws Exception{
        http
        //.cors(cors->cors.disable())
        .csrf(csrf->csrf.disable())
        .authorizeHttpRequests(auth->auth
        .anyRequest().permitAll());
        
        return http.build();
    }
//     @Bean
//     public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//         authProvider.setUserDetailsService(userDetailsService);
//         authProvider.setPasswordEncoder(passwordEncoder());
//         return new ProviderManager(List.of(authProvider));
//     }

//     @Primary
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
//     @Bean
// public UserDetailsService userDetailsService(UserRepo userRepo) {
//     return username -> {
//         UserDetails userDetails = userRepo.findByName(username);
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found: " + username);
//         }
//         return new org.springframework.security.core.userdetails.User(
//             user.getUsername(),
//             user.getPassword(),
//             List.of(new SimpleGrantedAuthority(user.getUserRole()))
//         );
//     };
// }
}