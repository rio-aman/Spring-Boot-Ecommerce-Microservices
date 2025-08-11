package com.demo.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestTemplateAdapter;

import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class HttpInterfaceConfig {

    // it can be done by using three ways by WebClient, RestClient, or RestTemplate

    //  this when using WEB CLIENT
//    @Bean
//    public ProviderHttpInterface webClientHttpInterface(){
//
//        WebClient webClient = WebClient.builder()
//                .baseUrl("http://localhost:8081").build();
//        WebClientAdapter adapter = WebClientAdapter.create(webClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//        ProviderHttpInterface service = factory.createClient(ProviderHttpInterface.class);
//
//        return service;
//    }

    //  this when using REST CLIENT
//    @Bean
//    public ProviderHttpInterface restClientHttpInterface(){
//
//        RestClient restClient = RestClient.builder()
//                .baseUrl("http://localhost:8081").build();
//        RestClientAdapter adapter = RestClientAdapter.create(restClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//        ProviderHttpInterface service = factory.createClient(ProviderHttpInterface.class);
//
//        return service;
//    }

    //  this when using REST TEMPLATE
    @Bean
    public ProviderHttpInterface restTemplateHttpInterface(){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8081"));
        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        ProviderHttpInterface service = factory.createClient(ProviderHttpInterface.class);

        return service;
    }
}
