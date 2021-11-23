package com.panacademy.squad7.bluebank.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Operation(summary = "Get a simple hello message")
    @GetMapping(value = "/hello", produces = "text/plain")
    public static String getHello() {
        return "Hello to Blue Bank API!";
    }

}
