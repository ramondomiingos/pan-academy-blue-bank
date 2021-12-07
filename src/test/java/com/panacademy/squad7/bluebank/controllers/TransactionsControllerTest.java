package com.panacademy.squad7.bluebank.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
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
import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.domain.enums.TransactionType;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.DepositRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.LoginRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.TransferRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.WithdrawRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
public class TransactionsControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;
    
    private DepositRequest depositRequest;
    
    private TransferRequest transferRequest;
    
    private WithdrawRequest withdrawRequest;
        
    @Autowired
    public TransactionsControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
       this.mockMvc = mockMvc;
       this.objectMapper = objectMapper;
        depositRequest = new DepositRequest();
        depositRequest.setAgencyNumber(1234l);
        depositRequest.setAccountNumber(12345678l);
        depositRequest.setAccountDigit('x');
        depositRequest.setAmount(BigDecimal.valueOf(1000l));
        depositRequest.setAccountType(AccountType.SA);;
              
        transferRequest = new TransferRequest();
        transferRequest.setAgencyNumber(1234l);
        transferRequest.setAccountNumber(12345678l);
        transferRequest.setAccountDigit('x');
        transferRequest.setAmount(BigDecimal.valueOf(50l));
        transferRequest.setType(TransactionType.PIX);
        transferRequest.setAccountType(AccountType.SA);;
        
        withdrawRequest = new WithdrawRequest();
        withdrawRequest.setAmount(BigDecimal.valueOf(50l));
        withdrawRequest.setType(TransactionType.PIX);    
    }
    
    @Test
    @Order(1)
    public void whenGetTransactions_thenStatus200() throws Exception {
        mockMvc.perform(get("/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(2)
    public void whenPostTransactionsDeposit_thenStatus201() throws Exception { 
    	MvcResult result = mockMvc.perform(get("/accounts/{id}", 1)).andReturn();
        if (result.getResponse().getStatus() !=200){
            AccountsControllerTest cTest =  new AccountsControllerTest(mockMvc, objectMapper);
            cTest.whenPostAccounts_thenStatus201();
        }
    	mockMvc.perform(post("/transactions/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(depositRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(3)
    public void whenPostTransactionsTransferById_thenStatus201() throws Exception { 
        	MvcResult result = mockMvc.perform(get("/accounts/{id}", 1)).andReturn();
            if (result.getResponse().getStatus() !=200){
                AccountsControllerTest cTest =  new AccountsControllerTest(mockMvc, objectMapper);
                cTest.whenPostAccounts_thenStatus201();
            }
    	mockMvc.perform(post("/transactions/transfer/{idAccount}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(4)
    public void whenPostTransactionsWithdrawById_thenStatus201() throws Exception {
    	mockMvc.perform(post("/transactions/withdraw/{idAccount}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(withdrawRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(5)
    public void whenGetTransactionsById_thenStatus200() throws Exception {
        mockMvc.perform(get("/transactions/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(6)
    public void whenPostTransactionsDeposit_thenStatus400() throws Exception {
        depositRequest.setAgencyNumber(12345l);
    	mockMvc.perform(post("/transactions/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Parameters"));
    }
    
    @Test
    @Order(7)
    public void whenPostTransactionsTransferById_thenStatus400() throws Exception {
        transferRequest.setAgencyNumber(12345l);
    	mockMvc.perform(post("/transactions/transfer/{idAccount}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Parameters"));
    }
 
    @Test
    @Order(8)
    public void whenPostTransactionsWithdrawById_thenStatus400() throws Exception {
        withdrawRequest.setType(null);
    	mockMvc.perform(post("/transactions/withdraw/{idAccount}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(withdrawRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Parameters"));
    }
    
    @Test
    @Order(9)
    public void whenGetTransactionsByIdExtract_thenStatus404() throws Exception {
    	mockMvc.perform(get("/transactions/{id}", 100)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(depositRequest)))
        .andExpect(status().isNotFound())
        .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").exists())
        .andExpect(jsonPath("$.message").value("transaction not found with id 100"));
    }
    
    @Test
    @Order(10)
    public void whenPostTransactionsDeposit_thenStatus404() throws Exception {
        depositRequest.setAgencyNumber(1235l); 
    	mockMvc.perform(post("/transactions/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists());           
    }
    
    @Test
    @Order(11)
    public void whenPostTransactionsTransferById_thenStatus404() throws Exception {
    	transferRequest.setAgencyNumber(1235l);
    	mockMvc.perform(post("/transactions/transfer/{idAccount}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("account not found with id 100"));;           
    }
    
    @Test
    @Order(12)
    public void whenPostTransactionsWithdrawById_thenStatus404() throws Exception {
    	transferRequest.setAgencyNumber(1235l);
    	mockMvc.perform(post("/transactions/withdraw/{idAccount}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("account not found with id 100"));;           
    }
    
}
