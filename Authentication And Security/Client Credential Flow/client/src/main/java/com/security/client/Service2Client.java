package com.security.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Service2Client {

    private final RestTemplate rest;
    private final OAuth2AuthorizedClientManager manager;

    public Service2Client(RestTemplate rest, OAuth2AuthorizedClientManager manager) {
        this.rest = rest;
        this.manager = manager;
    }

    @Value("${service2.url}")
    String service2Url;

    public String fetchData() {

//        var authRequest = OAuth2AuthorizeRequest
//                .withClientRegistrationId("keycloak-client")
//                .principal("machine")
//                .build();
//
//        var client = manager.authorize(authRequest); // this will give the token from key cloak
//        String token = client.getAccessToken().getTokenValue();
//        System.out.println("TOKEN VALUE : " + token);
//
//        // this making because this token has to be sent on headers or passed for authentication
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(token);
//
//        // by this we're giving a request to that url
//        var resp = rest.exchange(
//                service2Url + "/data",
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                String.class
//        );
//        return resp.getBody();
        // THIS ABOVE WHEN TOKEN GETS FROM KEYCLOAK THE NEW TOKEN GENERATES FOR ACCESSING THE RESOURCES OR RESOURCE SERVICE

        // THIS ABOVE WHEN TOKEN GETS FROM KEYCLOAK AND FORWARD IT AND USE IT INSTEAD CREATING NEW

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String incomingToken = null;

        if(auth instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            incomingToken = jwtAuthenticationToken.getToken().getTokenValue();
        }

        System.out.println("TOKEN VALUE : " + incomingToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(incomingToken);

        var resp = rest.exchange(
                service2Url + "/data",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );
        return resp.getBody();
    }
}

//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//String incomingToken = null;
//
//        if(auth instanceof JwtAuthenticationToken jwtAuthenticationToken) {
//incomingToken = jwtAuthenticationToken.getToken().getTokenValue();
//        }

// for above the working

//Whenever you are being or your request is being authenticated, there is a authentication object created
//by Spring Security.
//And it exists in the security context.
//So that authentication object which indicates that the request is authenticated is populated in
//the spring security context okay.
//        And this object has the details of the authenticated user.
//So what we are doing is we are retrieving that object from the security context.
//That security context is available for us to access.
//And we are getting that authentication object.
//And within that we have access to the token.
//So we get the token.
//        So this is a token now that we are getting in from the request okay.
//From the postman.
//And then what we would do is we would actually create the HTTP headers like we are creating HTTP headers.
//And since we have the token, you can actually use this same code from creating HTTP headers to forming
//the Rest endpoint, to making you to getting the body of the request response.
//So just add it over here and instead of set bearer auth as token you can say incoming token over here.