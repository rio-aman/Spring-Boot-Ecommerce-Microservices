package com.demo.consumer.httpinterfaces;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class EurekaHttpInterfaceConfig {

    // it can be done by using three ways by WebClient, RestClient, or RestTemplate

    //  this when using WEB CLIENT

    // using eureka server with Web Client of http interface
    @Bean
    @LoadBalanced
    public WebClient.Builder webBuilder() {
        return WebClient.builder();
    }

    @Bean
    public EurekaProviderHttpInterface webClientHttpInterface(WebClient.Builder webBuilder) {

//        WebClient webClient = WebClient.builder()
//                .baseUrl("http://localhost:8081").build();
        // above when no eureka server used for this no argument there
//
//        // here using the eureka server for this some arguments added in method
        WebClient web = webBuilder.baseUrl("http://provider").build();
        WebClientAdapter adapter = WebClientAdapter.create(web);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        EurekaProviderHttpInterface service = factory.createClient(EurekaProviderHttpInterface.class);

        return service;
    }

    //  this when using REST CLIENT

    // using eureka server with Rest Client of http interface
//    @Bean
//    @LoadBalanced
//    public RestClient.Builder restClientBuilder(){
//        return  RestClient.builder();
//    }
//
//    @Bean
//    public EurekaProviderHttpInterface restClientHttpInterface(RestClient.Builder builder){
//
////        RestClient restClient = RestClient.builder()
////                .baseUrl("http://localhost:8081").build();
//        // above when no eureka server used for this no argument there
//
//        // here using the eureka server for this some arguments added in method
//        RestClient restClient = builder.baseUrl("http://provider").build();
//        RestClientAdapter adapter = RestClientAdapter.create(restClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//        EurekaProviderHttpInterface service = factory.createClient(EurekaProviderHttpInterface.class);
//
//        return service;
//    }


    //  this when using REST TEMPLATE

    // using eureka server with Rest Template of http interface
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
//
//    @Bean
//    public EurekaProviderHttpInterface restTemplateHttpInterface(RestTemplate restTemplate){
//
////        RestTemplate restTemplate = new RestTemplate();
//        // above when no eureka server used for this no argument there
//
//        // here using the eureka server for this some arguments added in method
//        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://provider"));
//        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//        EurekaProviderHttpInterface service = factory.createClient(EurekaProviderHttpInterface.class);
//
//        return service;
//    }
}
