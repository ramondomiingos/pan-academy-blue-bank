package com.panacademy.squad7.bluebank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.TransactionType;
import com.panacademy.squad7.bluebank.web.dtos.request.DepositRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.TransferRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.WithdrawRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
class TransactionsControllerTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final DepositRequest depositRequest;

    private final TransferRequest transferRequest;

    private final WithdrawRequest withdrawRequest;

    @Autowired
    public TransactionsControllerTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        depositRequest = new DepositRequest();
        depositRequest.setAgencyNumber(1234L);
        depositRequest.setAccountNumber(1L);
        depositRequest.setAccountDigit('9');
        depositRequest.setAmount(BigDecimal.valueOf(1000L));
        depositRequest.setAccountType(AccountType.CA);

        transferRequest = new TransferRequest();
        transferRequest.setAgencyNumber(1234L);
        transferRequest.setAccountNumber(1L);
        transferRequest.setAccountDigit('9');
        transferRequest.setAmount(BigDecimal.valueOf(50L));
        transferRequest.setType(TransactionType.PIX);
        transferRequest.setAccountType(AccountType.CA);

        withdrawRequest = new WithdrawRequest();
        withdrawRequest.setAmount(BigDecimal.valueOf(50L));
        withdrawRequest.setType(TransactionType.PIX);
    }

    @Test
    @Order(1)
    void whenGetTransactions_thenStatus200() throws Exception {
        mockMvc.perform(get("/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    void whenPostTransactionsDeposit_thenStatus201() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/{id}", 1)).andReturn();
        if (result.getResponse().getStatus() != 200) {
            AccountsControllerTests aTest = new AccountsControllerTests(mockMvc, objectMapper);
            aTest.whenPostAccount_thenStatus201();
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
    void whenPostTransactionsTransferById_thenStatus201() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/{id}", 1)).andReturn();
        if (result.getResponse().getStatus() != 200) {
            AccountsControllerTests aTest = new AccountsControllerTests(mockMvc, objectMapper);
            aTest.whenPostAccount_thenStatus201();
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
    void whenPostTransactionsWithdrawById_thenStatus201() throws Exception {
        mockMvc.perform(post("/transactions/withdraw/{idAccount}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(withdrawRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(5)
    void whenGetTransactionsById_thenStatus200() throws Exception {
        mockMvc.perform(get("/transactions/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(6)
    void whenPostTransactionsDeposit_thenStatus400() throws Exception {
        depositRequest.setAgencyNumber(12345L);
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
    void whenPostTransactionsTransferById_thenStatus400() throws Exception {
        transferRequest.setAgencyNumber(12345L);
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
    void whenPostTransactionsWithdrawById_thenStatus400() throws Exception {
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
    void whenGetTransactionsByIdExtract_thenStatus404() throws Exception {
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
    void whenPostTransactionsDeposit_thenStatus404() throws Exception {
        depositRequest.setAgencyNumber(1235L);
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
    void whenPostTransactionsTransferById_thenStatus404() throws Exception {
        transferRequest.setAgencyNumber(1235L);
        mockMvc.perform(post("/transactions/transfer/{idAccount}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("account not found with id 100"));
    }

    @Test
    @Order(12)
    void whenPostTransactionsWithdrawById_thenStatus404() throws Exception {
        transferRequest.setAgencyNumber(1235L);
        mockMvc.perform(post("/transactions/withdraw/{idAccount}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("account not found with id 100"));
    }

}
