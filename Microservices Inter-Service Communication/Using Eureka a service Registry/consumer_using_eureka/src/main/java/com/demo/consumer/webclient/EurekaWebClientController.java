package com.demo.consumer.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/web-client")
@RequiredArgsConstructor
public class EurekaWebClientController {

    private final EurekaWebClientProviderWebClient providerWebClient;

    @GetMapping("/instance")
    public Mono<String> getInstance(){
//        WebClient webClient = WebClient.create();
//        Mono<String> response = webClient.get()
//                .uri("http://localhost:8081/instance-info")
//                .retrieve()
//                .bodyToMono(String.class);
//        return response;

//        now using bean of webclient
        return providerWebClient.getInstanceInfo();
    }
}
