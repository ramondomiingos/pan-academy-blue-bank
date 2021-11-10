package com.panacademy.squad7.bluebank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

    @GetMapping("/hello")
    public static String getHello(){
        return "Hello to Blue Bank API!";
    }

}
