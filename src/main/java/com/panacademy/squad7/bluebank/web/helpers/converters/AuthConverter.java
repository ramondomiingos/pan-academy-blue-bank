package com.panacademy.squad7.bluebank.web.helpers.converters;

import com.panacademy.squad7.bluebank.configs.security.JwtUtils;
import com.panacademy.squad7.bluebank.domain.models.User;
import com.panacademy.squad7.bluebank.web.dtos.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthConverter {
    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse toResponse(Authentication authentication) {
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getUsername())
                .roles(roles)
                .token(jwt)
                .build();
    }
}
