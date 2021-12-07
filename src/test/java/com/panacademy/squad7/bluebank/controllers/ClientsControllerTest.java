package com.panacademy.squad7.bluebank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
public class ClientsControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final ClientRequest clientRequest;

    @Autowired
    public ClientsControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;

        clientRequest = new ClientRequest();
        clientRequest.setName("Fulano");
        clientRequest.setLastname("de Tal");
        clientRequest.setBirthDate(LocalDate.of(1990, 12, 18));
        clientRequest.setMotherName("Fulano");
        clientRequest.setEmail("fulano@email.com");
        clientRequest.setPassword("fulano123");
        clientRequest.setPhone("11867543217");
        clientRequest.setCellphone("11986754321");
        clientRequest.setType(ClientType.NP);
        clientRequest.setRegistration("38441875880");
    }

    @Test
    @Order(1)
    public void whenGetClients_thenStatus200() throws Exception {
        mockMvc.perform(get("/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    public void whenPostClients_thenStatus201() throws Exception {
        MvcResult result = mockMvc.perform(get("/clients/{id}", 1)).andReturn();
        if (result.getResponse().getStatus() != 200) {
            mockMvc.perform(post("/clients")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(clientRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(content()
                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

    }

    @Test
    @Order(3)
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
    @Order(4)
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

    @Test
    @Order(5)
    public void whenPostClients_thenStatus400() throws Exception {
        clientRequest.setPhone("9999999999999999");
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Parameters"));
    }

    @Test
    @Order(6)
    public void whenPutClients_thenStatus404() throws Exception {
        clientRequest.setPhone("11867543217");
        mockMvc.perform(put("/clients/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("client not found with id 100"));
    }

    @Test
    @Order(7)
    public void whenPutClients_thenStatus400() throws Exception {
        clientRequest.setPhone("9999999999999999");
        mockMvc.perform(put("/clients/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Parameters"));
    }

    @Test
    @Order(8)
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
    @Order(9)
    public void whenDeleteClients_thenStatus404() throws Exception {
        mockMvc.perform(delete("/clients/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("client not found with id 100"));
    }

    @Test
    @Order(10)
    public void whenDeleteClients_thenStatus204() throws Exception {
        MvcResult result = mockMvc.perform(get("/clients/{id}", 1)).andReturn();
        if (result.getResponse().getStatus() != 200) {
            mockMvc.perform(delete("/clients/{id}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(clientRequest)))
                    .andExpect(status().isNoContent());
        }
    }
}
