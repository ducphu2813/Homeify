package com.homeify.apigateway.APIGateway.Configuration;

import com.homeify.apigateway.APIGateway.Filter.RouteValidator;
import com.homeify.apigateway.APIGateway.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RouteValidator routeValidator;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                // ...
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

}
