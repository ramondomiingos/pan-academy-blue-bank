package com.panacademy.squad7.bluebank.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.web.dtos.request.AccountRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
public class AccountsControllerTest {

	private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;
    
    private AccountRequest accountRequest;
    
    @Autowired
    public AccountsControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
       this.mockMvc = mockMvc;
       this.objectMapper = objectMapper;
       accountRequest = new AccountRequest();
       accountRequest.setAgencyNumber(1234l);
       accountRequest.setAccountNumber(12345678l);
       accountRequest.setAccountDigit('x');
       accountRequest.setBalance(BigDecimal.valueOf(1000l));
       accountRequest.setType(AccountType.CA);
       accountRequest.setStatus(StatusType.A);
       accountRequest.setClientId(1L);
   }  
    
    @Test
    @Order(1)
    public void whenGetAccounts_thenStatus200() throws Exception {
        mockMvc.perform(get("/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(2)
    public void whenPostAccounts_thenStatus201() throws Exception {
    	MvcResult result = mockMvc.perform(get("/clients/{id}", 1)).andReturn();
        if (result.getResponse().getStatus() !=200){
            ClientsControllerTest cTest =  new ClientsControllerTest(mockMvc, objectMapper);
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
    public void whenGetAccountsById_thenStatus200() throws Exception {
        mockMvc.perform(get("/accounts/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(4)
    public void whenGetAccountsByIdExtract_thenStatus200() throws Exception {
        mockMvc.perform(get("/accounts/{id}/extract", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(5)
    public void whenPostAccounts_thenStatus400() throws Exception {
        accountRequest.setAgencyNumber(12345l);
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
    @Order(6)
    public void whenPostAccounts_thenStatus404() throws Exception {
    	accountRequest.setClientId(2l); 
    	accountRequest.setAgencyNumber(1234l); 
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
    @Order(7)
    public void whenGetAccountsById_thenStatus404() throws Exception {
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
    @Order(8)
    public void whenGetAccountsByIdExtract_thenStatus404() throws Exception {
    	mockMvc.perform(get("/accounts/{id}/extract", 100)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountRequest)))
        .andExpect(status().isNotFound())
        .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").exists())
        .andExpect(jsonPath("$.message").value("account not found with id 100"));
    }
    
}
