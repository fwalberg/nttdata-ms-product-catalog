package com.nttdata.dio.apigateway.config;

import com.nttdata.dio.apigateway.filter.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayRoutesConfiguration {
    private final AuthFilter authFilter;

    public GatewayRoutesConfiguration(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-get-route", r -> r.path("/products/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://product-service"))

                .route("product-route", r -> r.path("/products/**")
                        .and()
                        .method(HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE)
                        .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
                        .uri("lb://product-service"))

                .route("order-route", r -> r.path("/orders/**")
                        .and()
                        .method(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE)
                        .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
                        .uri("lb://order-service"))

                .build();
    }
}
