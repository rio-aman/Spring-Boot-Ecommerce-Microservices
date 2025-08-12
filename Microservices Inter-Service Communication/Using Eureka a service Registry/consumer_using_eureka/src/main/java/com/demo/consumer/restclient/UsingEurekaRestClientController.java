package com.demo.consumer.restclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest-client")
@RequiredArgsConstructor
public class UsingEurekaRestClientController {

    private final UsingEurekaProviderRestClient usingEurekaProviderRestClient;

    @GetMapping("/instance")
    public String getInstance(){
//        RestClient restClient = RestClient.create();
//        String response = restClient.get()
//                .uri("http://localhost:8081/instance-info")
//                .retrieve()
//                .body(String.class);
//        return response;
//        this above when not using bean or RestClientConfig and Client also

//        now using these
        return usingEurekaProviderRestClient.getInstanceInfo();
    }
}
