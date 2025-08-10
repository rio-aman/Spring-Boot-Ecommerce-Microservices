package com.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerApplicationByOpenFeign {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplicationByOpenFeign.class, args);
	}

}
