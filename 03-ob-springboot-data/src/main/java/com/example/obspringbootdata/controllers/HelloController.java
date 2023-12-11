package com.example.obspringbootdata.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("hola")
    public String helloWorld() {
        return "Hello world,  whats up!";
    }
}
