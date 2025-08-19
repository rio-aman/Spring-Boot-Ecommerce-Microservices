package com.ecommerce.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class FallBackController {

    @GetMapping("/fallback/products")
    public ResponseEntity<List<String>> productsFallBack(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.singletonList("Product Service Is Unavailable Please Try again After Sometime"));
    }

    @GetMapping("/fallback/users")
    public ResponseEntity<List<String>> usersFallBack(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.singletonList("User Service Is Unavailable Please Try again After Sometime"));
    }

}
