package com.panacademy.squad7.bluebank.web.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class JwtResponse {
    private static final String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String token;
}
