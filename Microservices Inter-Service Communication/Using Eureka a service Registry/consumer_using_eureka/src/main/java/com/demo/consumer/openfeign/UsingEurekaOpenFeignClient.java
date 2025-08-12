package com.demo.consumer.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(name = "provider-service", url = "http://localhost:8081")
//before using the eureka

@FeignClient(name = "provider")
public interface UsingEurekaOpenFeignClient {

    @GetMapping("/instance-info")
    String getInstanceInfo();
}

//why we don't used the load balanced in open feign because the open feign is already
//integrate the spring cloud load balance when it's making use of service name basically it's inbuilt