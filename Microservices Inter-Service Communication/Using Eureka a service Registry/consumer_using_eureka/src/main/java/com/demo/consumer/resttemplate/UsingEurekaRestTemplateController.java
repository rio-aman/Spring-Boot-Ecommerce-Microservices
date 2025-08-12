package com.demo.consumer.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest-template")
@RequiredArgsConstructor
public class UsingEurekaRestTemplateController {

    private final UsingEurekaRestTemplateClient usingEurekaRestTemplateClient;
    @GetMapping("/instance")
    public String getInstance(){
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8081/instance-info", String.class);
//        return response;
//        Above when not creating its bean

//        after using the bean
        return usingEurekaRestTemplateClient.getInstanceInfo();

    }
}
