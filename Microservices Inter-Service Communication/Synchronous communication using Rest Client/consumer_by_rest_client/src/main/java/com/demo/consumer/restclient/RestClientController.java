package com.demo.consumer.restclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/rest-client")
public class RestClientController {

    @GetMapping("/instance")
    public String getInstance(){
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri("http://localhost:8081/instance-info")
                .retrieve()
                .body(String.class);
        return response;
    }
}
