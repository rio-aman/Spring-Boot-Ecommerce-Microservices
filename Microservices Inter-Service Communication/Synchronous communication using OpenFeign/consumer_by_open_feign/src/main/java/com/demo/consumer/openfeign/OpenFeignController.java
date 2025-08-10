package com.demo.consumer.openfeign;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feign")
@RequiredArgsConstructor
public class OpenFeignController {

    private final OpenFeignClient providerFeignClient;

    @GetMapping("/instance")
    public String getInstance(){
        return providerFeignClient.getInstanceInfo();
    }
}
