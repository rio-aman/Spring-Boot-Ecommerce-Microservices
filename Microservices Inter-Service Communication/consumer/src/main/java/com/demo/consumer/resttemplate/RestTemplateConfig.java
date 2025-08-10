package com.demo.consumer.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
//    BY CREATING THIS TYPE OF BEAN OF REST-TEMPLATE SO THEN THIS REST-TEMPLATE INSTANCE WILL CREATE ONCE AND USED BY ENTIRE APPLICATION
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
