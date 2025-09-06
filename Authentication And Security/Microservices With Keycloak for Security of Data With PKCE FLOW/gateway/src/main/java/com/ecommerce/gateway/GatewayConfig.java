package com.ecommerce.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    // using redis rate limiter
    @Bean
    public KeyResolver hostnameKeyResolver(){
        return exchange -> Mono.just
                (exchange.getRequest().getRemoteAddress().getHostName());
    }

    @Bean
    public RedisRateLimiter redisRateLimiter(){
//        return new RedisRateLimiter(1,1,1);

        return new RedisRateLimiter(10,20,1);
//  So the replenish rate is ten which means the bucket is refilled with ten tokens per second.
//  Default burst capacity is 20, which means the bucket can hold up to 20 tokens, allowing for short bursts of up to 20 requests at once.
//  So 20 requests at once can hit the server. And default request token is being set to one, which means that each request will typically
//  consume one token.
    }

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
                        .filters(f -> f
                                .retry(retryConfig -> retryConfig
                                        .setRetries(10).setMethods(HttpMethod.GET))
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(hostnameKeyResolver()))
                                .circuitBreaker(config -> config
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
                .route("order-cart-service", r -> r
                        .path("/api/orders/**","/api/cart/**")
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
