package com.panacademy.squad7.bluebank.web.helpers.converters;

import com.panacademy.squad7.bluebank.domain.enums.RoleType;
import com.panacademy.squad7.bluebank.domain.models.User;
import com.panacademy.squad7.bluebank.exceptions.InvalidInputException;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toModel(ClientRequest clientRequest) {
        User user = User.builder()
                .username(clientRequest.getEmail())
                .role(RoleType.U)
                .build();
        if (clientRequest.getPassword() != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(clientRequest.getPassword()));
            return user;
        } else {
            throw new InvalidInputException("the password field needs to be filled");
        }
    }
}
