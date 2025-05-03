package com.examly.springapp.config;
 

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;



import io.swagger.v3.oas.models.Components;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Contact;

import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.security.SecurityRequirement;

import io.swagger.v3.oas.models.security.SecurityScheme;



@Configuration

public class SwaggerConfig {

        @Bean

        public OpenAPI openAPI() {

                return new OpenAPI()

                .info(new Info()

                .title("Invest Track")

                .version("1.0.0")

                .contact(new Contact()
                                .name("Demo API Support")

                                .email("support@investtrack.com")

                                .url("#"))

                                .description("Invest Track API allows users to explore, invest, and manage their investments. It provides a user driven feedback"))

                                .addSecurityItem(new SecurityRequirement()

                                .addList("Bearer Authentication"))

                                .components(new Components()

                                .addSecuritySchemes("Bearer Authentication", new SecurityScheme()

                                .type(SecurityScheme.Type.HTTP)

                                .bearerFormat("JWT")
                                .scheme("bearer")));

                        }

                }


                