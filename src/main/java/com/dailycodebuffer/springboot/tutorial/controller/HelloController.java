package com.dailycodebuffer.springboot.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${com.tsc.config}")
    private String tscConfig;

    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello World!! " + tscConfig;
    }

}
