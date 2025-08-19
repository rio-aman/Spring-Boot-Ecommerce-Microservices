package com.ecommerce.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
//                .route("product-service", r -> r
//                        .path("/api/products/**")
//                        .uri("lb://PRODUCT-SERVICE"))
//                .route("user-service", r -> r
//                        .path("/api/users/**")
//                        .uri("lb://USER-SERVICE"))
//                .route("order-&-cart-service", r -> r
//                        .path("/api/orders/**","/api/cart/**")
//                        .uri("lb://ORDER-CART-SERVICE"))

                // above is normal routing with the eureka registered services name

                    // when want to switch the path ore rewrite the path means removing /api then the below code is written

//                .route("product-service", r -> r
//                        .path("/products/**")
//                        .filters(f-> f.rewritePath("/products(?<segment>/?.*)",
//                                "/api/products${segment}"))
//                        .uri("lb://PRODUCT-SERVICE"))
//                .route("user-service", r -> r
//                        .path("/users/**")
//                        .filters(f-> f.rewritePath("/users(?<segment>/?.*)",
//                                "/api/users${segment}"))
//                        .uri("lb://USER-SERVICE"))
//                .route("order-cart-service", r -> r
//                        .path("/orders/**","/cart/**")
//                        .filters(f-> f.rewritePath("/(?<segment>.*)",
//                                "/api/${segment}"))
//                        .uri("lb://ORDER-CART-SERVICE"))
                // above is the rewrite path of the services


                // using the circuit breaker for Api Gateway

                .route("product-service", r -> r
                        .path("/api/products/**")
                        .filters(f -> f.circuitBreaker
                                (config -> config
                                        .setName("ecomBreaker")
                                        .setFallbackUri("forward:/fallback/products")))
                        .uri("lb://PRODUCT-SERVICE"))
                .route("user-service", r -> r
                        .path("/api/users/**")
                        .filters(f -> f.circuitBreaker
                                (config -> config
                                        .setName("ecomBreaker")
                                        .setFallbackUri("forward:/fallback/users")))
                        .uri("lb://USER-SERVICE"))
                .route("order-&-cart-service", r -> r
                        .path("/api/orders/**","/api/cart/**")
                        .filters(f -> f.circuitBreaker
                                (config -> config
                                        .setName("ecomBreaker")
                                        .setFallbackUri("forward:/fallback/orders")))
                        .uri("lb://ORDER-CART-SERVICE"))

                // above using the circuit breaker for Gateway Service

                .route("eureka-server", r -> r
                        .path("/eureka/main")
                        .filters(f -> f.rewritePath("/eureka/main", "/"))
                        .uri("http://localhost:8761"))
                .route("eureka-server-static", r -> r
                        .path("/eureka/**")
                        .uri("http://localhost:8761"))
                .build();
    }
}
