package com.demo.consumer.restclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class UsingEurekaRestClientConfig {

    // Load-balanced builder so service IDs resolve via Eureka
    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

//    @LoadBalanced is on the RestClient bean, but Spring Cloud LoadBalancer
//    only works if @LoadBalanced is applied to a RestClient.Builder bean, not to the RestClient itself.

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl("http://provider")
                .build();
    }
}
