package com.example.demo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping ("/api")
public class HelloApiRestController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot API!";
    }
    
}
