package com.kafka.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FunctionsClass {

    @Bean
    public Function<String, String> uppercase(){
        return value -> value.toUpperCase(); // lambda function way
//        return String::toUpperCase;   // this is a method reference type way to return the value
    }
}
