package com.homeify.apigateway.APIGateway.Configuration;

import com.homeify.apigateway.APIGateway.Filter.AuthenticationFilter;
import com.homeify.apigateway.APIGateway.Filter.RouteValidator;
import com.homeify.apigateway.APIGateway.Service.JWTService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public AuthenticationFilter authenticationFilter(JWTService jwtService
            , RouteValidator routeValidator) {
        return new AuthenticationFilter(jwtService, routeValidator);
    }

    //tạo route locator
    //cần tạo tất cả các route cho từng controller
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder
            , AuthenticationFilter authenticationFilter) {


        return builder.routes()
                .route("auth_users_route", r -> r.path("/api/users/**")     // định nghĩa route đến 1 controller cụ thể
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))   // add filter jwt
                        .uri("lb://Auth.API"))  // tên service đăng ký trên eureka

                .route("auth_roles_route", r -> r.path("/api/roles/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Auth.API"))

                .route("auth_auth_route", r -> r.path("/api/auth/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Auth.API"))

                .route("service_info_city_route", r -> r.path("/api/city/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://ServiceInfo.API"))

                .route("service_info_transportation_route", r -> r.path("/api/transportation/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://ServiceInfo.API"))

                .route("service_info_seat_route", r -> r.path("/api/seat/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://ServiceInfo.API"))

                .route("service_info_trip_info_route", r -> r.path("/api/trip-info/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://ServiceInfo.API"))

                .route("service_info_pickuparea_route", r -> r.path("/api/pickuparea/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://ServiceInfo.API"))

                .route("service_info_trip_route", r -> r.path("/api/trip/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://ServiceInfo.API"))


                .route("booking_booking_route", r -> r.path("/api/booking/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Booking.API"))

                .route("trip_booking_route", r -> r.path("/api/trip-booking/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Booking.API"))

                .build();

    }

}
