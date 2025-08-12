package com.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EurekaWithOpenFeignConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaWithOpenFeignConsumerApplication.class, args);
	}

}
