package com.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class RestClientConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestClientConsumerApplication.class, args);
	}

}
