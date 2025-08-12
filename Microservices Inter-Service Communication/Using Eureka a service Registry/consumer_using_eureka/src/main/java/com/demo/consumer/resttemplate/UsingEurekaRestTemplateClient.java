package com.demo.consumer.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UsingEurekaRestTemplateClient {

//    using eureka here
    private final RestTemplate restTemplate;
//    private final String PROVIDER_URL ="http://localhost:8081";

//    instead giving the localhost url give only service name
//    for working this in config this annotation added @LoadBalanced
    private final String PROVIDER_URL ="http://provider";

    public String getInstanceInfo(){
        return restTemplate.getForObject(PROVIDER_URL+"/instance-info",String.class);
    }
}
