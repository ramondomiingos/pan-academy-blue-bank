package com.panacademy.squad7.bluebank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.web.dtos.request.AccountRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.AccountUpdateRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
class AccountsControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final AccountRequest accountRequest;

    private final AccountUpdateRequest accountUpdateRequest;

    @Autowired
    public AccountsControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        accountRequest = new AccountRequest();
        accountRequest.setAgencyNumber(1234L);
        accountRequest.setType(AccountType.CA);
        accountRequest.setClientId(1L);

        accountUpdateRequest = new AccountUpdateRequest();
        accountUpdateRequest.setStatus(StatusType.A);
    }

    @Test
    @Order(1)
     void whenGetAccounts_thenStatus200() throws Exception {
        mockMvc.perform(get("/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
     void whenPostAccount_thenStatus201() throws Exception {
        MvcResult result = mockMvc.perform(get("/clients/{id}", 1)).andReturn();
        if (result.getResponse().getStatus() != 200) {
            ClientsControllerTest cTest = new ClientsControllerTest(mockMvc, objectMapper);
            cTest.whenPostClients_thenStatus201();
        }
        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    void whenGetAccountsById_thenStatus200() throws Exception {
        mockMvc.perform(get("/accounts/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    @Order(4)
    void whenPutAccountsById_thenStatus201() throws Exception {
        accountUpdateRequest.setStatus(StatusType.A);
        mockMvc.perform(put("/accounts/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountUpdateRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(5)
    void whenGetAccountsByIdExtract_thenStatus200() throws Exception {
        accountUpdateRequest.setStatus(StatusType.A);
        mockMvc.perform(get("/accounts/{id}/extract", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(6)
    void whenPostAccounts_thenStatus400() throws Exception {
        accountRequest.setAgencyNumber(12345L);
        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Parameters"));
    }

    @Test
    @Order(7)
    void whenPutAccountsById_thenStatus400() throws Exception {
        accountRequest.setAccountNumber(12345L);
        mockMvc.perform(put("/accounts/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(8)
    void whenPostAccounts_thenStatus404() throws Exception {
        accountRequest.setClientId(2L);
        accountRequest.setAgencyNumber(1234L);
        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("client not found with id " + accountRequest.getClientId()));
    }

    @Test
    @Order(9)
    void whenGetAccountsById_thenStatus404() throws Exception {
        mockMvc.perform(get("/accounts/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("account not found with id 100"));
    }

    @Test
    @Order(10)
    void whenGetAccountsByIdExtract_thenStatus404() throws Exception {
        mockMvc.perform(get("/accounts/{id}/extract", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("account not found with id 100"));
    }

    @Test
    @Order(11)
    void whenPutAccountsById_thenStatus404() throws Exception {
        mockMvc.perform(put("/accounts/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountUpdateRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message").exists())
                        .andExpect(jsonPath("$.message").value("account not found with id 100"));
    }

    @Test
    @Order(12)
    void whenDeleteAccountsById_thenStatus404() throws Exception {
        mockMvc.perform(delete("/accounts/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("account not found with id 100"));
    }

    @Test
    @Order(13)
    void whenDeleteAccounts_thenStatus204() throws Exception {
        mockMvc.perform(delete("/accounts/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
