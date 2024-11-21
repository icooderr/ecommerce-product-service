package com.ecommerce.helloworld.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    public String hello() {
        return "Hello, Tony!";
    }
}
