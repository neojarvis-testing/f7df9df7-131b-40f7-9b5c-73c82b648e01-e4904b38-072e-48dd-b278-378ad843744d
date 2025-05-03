package com.examly.springapp.config;

import java.io.IOException;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;



import com.examly.springapp.service.UserService;



import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**

* JwtAuthenticationFilter intercepts incoming requests to authenticate users based on JWT tokens.

* It extends OncePerRequestFilter, ensuring execution once per request.

*/

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    
    private final JwtUtils jwtUtils;
    
    private final UserService service;


    
    /**
    
    * Constructor injection for JwtUtils and UserService.
    
    * 
    
    * @param jwtUtils JWT utility class for token operations.
    
    * @param service UserService for fetching user details.
    
    */
    
    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserService service) {
    
        this.jwtUtils = jwtUtils;
    
        this.service = service;
    
    }


    
    /**
    
    * Filters incoming requests to authenticate the user using a JWT token.
    
    *
    
    * @param request Incoming HTTP request.
    
    * @param response HTTP response.
    
    * @param filterChain Filter chain for further processing.
    
    * @throws ServletException, IOException
    
    */
    
    @Override
    
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    
    throws ServletException, IOException {
    
        
        
        // Extract the JWT token from the request
        
        String token = jwtUtils.extractToken(request);


        
        // Validate token and authenticate the user if the token is valid
        
        if (token != null && jwtUtils.validateToken(token)) {
        
            // Extract email (username) from the token
        
            String email = jwtUtils.extractUsername(token);


            
            // Load user details using extracted email
            
            UserDetails userDetails = service.loadUserByEmail(email);


            
            // Create an authentication token with user details and authorities
            
            UsernamePasswordAuthenticationToken authenticationToken =
            
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());


            
            // Set authentication details from the request
            
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set the authentication context in SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}