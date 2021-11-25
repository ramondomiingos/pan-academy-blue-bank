package com.panacademy.squad7.bluebank.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello", description = "endpoint for one simple test")
public class HomeController {

    @Operation(summary = "Get a simple hello message", responses = {@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping(value = "/hello", produces = "text/plain")
    public static String getHello() {
        return "Hello to Blue Bank API!";
    }

}
