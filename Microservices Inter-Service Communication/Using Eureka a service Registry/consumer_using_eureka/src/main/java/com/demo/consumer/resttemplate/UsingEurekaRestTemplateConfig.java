package com.demo.consumer.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UsingEurekaRestTemplateConfig {
//    BY CREATING THIS TYPE OF BEAN OF REST-TEMPLATE SO THEN THIS REST-TEMPLATE INSTANCE WILL CREATE ONCE AND USED BY ENTIRE APPLICATION

//    this load balancer annotaion added because the spring boot sees the provider in url as host name
//    and that host name is resitered in eureka with the localhost url so the eureka is need to handle this
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
