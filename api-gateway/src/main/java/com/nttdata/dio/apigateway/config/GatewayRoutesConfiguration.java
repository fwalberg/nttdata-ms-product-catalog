package com.nttdata.dio.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfiguration {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service-route", r -> r.path("/products/**")
                        .uri("lb://product-service"))
                .route("order-service-route", r -> r.path("/orders/**")
                        .uri("lb://order-service"))
                .build();
    }
}
