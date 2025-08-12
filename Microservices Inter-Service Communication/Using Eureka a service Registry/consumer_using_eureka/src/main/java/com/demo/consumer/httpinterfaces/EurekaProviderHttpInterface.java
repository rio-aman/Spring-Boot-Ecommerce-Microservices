package com.demo.consumer.httpinterfaces;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface EurekaProviderHttpInterface {

    @GetExchange("/instance-info")
    String getInstanceInfo();
}
