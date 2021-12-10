package com.panacademy.squad7.bluebank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacademy.squad7.bluebank.configs.security.JwtUtils;
import com.panacademy.squad7.bluebank.domain.enums.RoleType;
import com.panacademy.squad7.bluebank.domain.models.User;
import com.panacademy.squad7.bluebank.domain.repositories.UsersRepository;
import com.panacademy.squad7.bluebank.web.dtos.request.LoginRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final UsersRepository repository;

    private final LoginRequest loginRequest;

    @Autowired
     AuthControllerTests(MockMvc mockMvc, ObjectMapper objectMapper, JwtUtils jwtUtils, UsersRepository repository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.repository = repository;
        loginRequest = new LoginRequest("admin", "admin");
    }

    @BeforeAll
    void setUp(){
        Optional<User> optional = repository.findByUsername(loginRequest.getUsername());
        if(optional.isEmpty()){
            User user = new User();
            user.setUsername(loginRequest.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(loginRequest.getPassword()));
            user.setRole(RoleType.A);
            repository.save(user);
        }
    }

    @Test
    @Order(1)
     void whenGetAuthWithCorrectPassword_thenStatus200() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .content(objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
     void whenGetAuthWithIncorrectPassword_thenStatus403() throws Exception {
        loginRequest.setPassword("password");
        mockMvc.perform(post("/auth/login")
                        .content(objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
