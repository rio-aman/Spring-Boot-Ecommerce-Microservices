package com.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//when using openfeign or rest template or others comment this
@EnableFeignClients
public class UsingEurekaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsingEurekaConsumerApplication.class, args);
	}

}
