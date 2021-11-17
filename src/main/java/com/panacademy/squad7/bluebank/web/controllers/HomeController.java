package com.panacademy.squad7.bluebank.web.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public static String getHello() {
        return "Hello to Blue Bank API!";
    }

}
