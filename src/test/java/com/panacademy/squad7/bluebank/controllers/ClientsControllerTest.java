package com.panacademy.squad7.bluebank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
public class ClientsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ClientRequest clientRequest;

    @BeforeAll
    private void setUp() throws Exception {
        clientRequest = new ClientRequest();
        clientRequest.setName("Fulano");
        clientRequest.setLastname("de Tal");
        clientRequest.setBirthDate(LocalDate.of(1990, 12, 18));
        clientRequest.setMotherName("Fulano");
        clientRequest.setEmail("fulano@email.com");
        clientRequest.setPassword("fulano");
        clientRequest.setPhone("1186754321");
        clientRequest.setCellphone("11986754321");
        clientRequest.setType(ClientType.NP);
        clientRequest.setRegistration("38441875880");
    }

    @Test
    public void whenPostClients_thenStatus201() throws Exception {
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test

    public void whenGetClients_thenStatus200() throws Exception {
        mockMvc.perform(get("/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    //@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
    public void whenGetClientById_thenStatus200() throws Exception {
        mockMvc.perform(get("/clients/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(clientRequest.getName()));
    }

    @Test
    //@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
    public void whenGetClientById_thenStatus404() throws Exception {
        mockMvc.perform(get("/clients/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("client not found with id 100"));
    }

    @Test
    //@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
    public void whenPutClients_thenStatus201() throws Exception {
        clientRequest.setName("Sicrano");
        mockMvc.perform(put("/clients/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Sicrano"));
    }
}
