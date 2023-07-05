package com.hyundaimotors.hmb.cdppapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig{
    

    @Autowired
    private JwtExceptionFilter JwtExceptionFilter;

    //private static final String[] SWAGGER_PATHS = {"/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/webjars/swagger-ui/**"};
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                //.requestMatchers(SWAGGER_PATHS).permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer()
            .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint())
            .jwt();
        return http.build();
    }
}


