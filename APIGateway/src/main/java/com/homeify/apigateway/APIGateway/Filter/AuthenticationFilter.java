package com.homeify.apigateway.APIGateway.Filter;

import com.homeify.apigateway.APIGateway.Exceptions.ForbiddenException;
import com.homeify.apigateway.APIGateway.Exceptions.UnauthorizedException;
import com.homeify.apigateway.APIGateway.Service.JWTService;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JWTService jwtService;
    private final RouteValidator routeValidator;


    public AuthenticationFilter(JWTService jwtService
                                , RouteValidator routeValidator) {
        super(Config.class);
        this.jwtService = jwtService;
        this.routeValidator = routeValidator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            System.out.println("Đã tới phần filter");

            ServerHttpRequest request = exchange.getRequest();
            // bỏ qua xác thực cho các route mở
            if (!routeValidator.isSecured.test(request)) {
                System.out.println("url này không cần jwt");
                return chain.filter(exchange);
            }
            //header có token hay ko
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new UnauthorizedException("Missing authorization header");
            }

            // lấy token từ header "Authorization"
            String authHeader = null;
            List<String> authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
            if (authHeaders != null && !authHeaders.isEmpty()) {
                authHeader = authHeaders.get(0);
            }
            String token = null;
            String username = null;
            List<String> roles = null;
            String csrfToken = exchange.getRequest().getHeaders().getFirst("X-CSRF-TOKEN");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);            // lấy phần sau "Bearer "
                username = jwtService.extractUsername(token);       //lấy username từ token
                roles = jwtService.extractRoles(token);            //lấy roles từ token
            }

            if (token != null && jwtService.validateToken(token)) {

                //phần này test role của user
//                if(roles.stream().noneMatch(role -> role.equals("ADMIN"))){
//                    throw new ForbiddenException("Access denied");
//                }
//                System.out.println("token hợp lệ");
                // token hợp lệ, add username, roles vào header
                exchange.getRequest().mutate()
                        .header("X-Username", username)
                        .header("X-Roles", String.join(",", roles))
                        .build();

                // tiếp tục reqeust
                System.out.println("đi tiếp");
                return chain.filter(exchange);
            } else {
                //token ko hợp lệ
                System.out.println("token ko hợp lệ");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {

    }
}
