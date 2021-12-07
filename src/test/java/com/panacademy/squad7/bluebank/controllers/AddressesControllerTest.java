package com.panacademy.squad7.bluebank.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacademy.squad7.bluebank.web.dtos.request.AddressRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "test", authorities = {"ROLE_USER", "ROLE_ADMIN"})
public class AddressesControllerTest {

	private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;
    
    private AddressRequest addressRequest;
    
    @Autowired
    public AddressesControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
       this.mockMvc = mockMvc;
       this.objectMapper = objectMapper;
       addressRequest = new AddressRequest();
       addressRequest.setAddress("Avenida Paulista");
       addressRequest.setNumber("1001");
       addressRequest.setCity("São Paulo");
       addressRequest.setState("SP");
       addressRequest.setClientId(1L);
   }
        
    @Test
    @Order(1)
    public void whenGetAddresses_thenStatus200() throws Exception {
        mockMvc.perform(get("/addresses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(2)
    public void whenPostAddresses_thenStatus201() throws Exception {
    	MvcResult result = mockMvc.perform(get("/clients/{id}", 1)).andReturn();
        if (result.getResponse().getStatus() !=200){
            ClientsControllerTest cTest =  new ClientsControllerTest(mockMvc, objectMapper);
            cTest.whenPostClients_thenStatus201();
        }
        mockMvc.perform(post("/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(3)
    public void whenGetAddressesById_thenStatus200() throws Exception {
    	mockMvc.perform(get("/addresses/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(4)
    public void whenPutAddresses_thenStatus201() throws Exception {
    	addressRequest.setAddress("Avenida Rio de Janeiro");
        mockMvc.perform(put("/addresses/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.address").value("Avenida Rio de Janeiro"));
    }
    
    @Test
    @Order(5)
    public void whenPostAddresses_thenStatus400() throws Exception {
        addressRequest.setState("AAA");
    	mockMvc.perform(post("/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Parameters"));
    }
    
    @Test
    @Order(6)
    public void whenPostAddresses_thenStatus404() throws Exception {
    	addressRequest.setClientId(2l); 
    	addressRequest.setState("SP");
    	mockMvc.perform(post("/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("client not found with id " + addressRequest.getClientId()));
    }    
    
    @Test
    @Order(7)
    public void whenGetAddressesById_thenStatus404() throws Exception {
    	       mockMvc.perform(get("/addresses/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("address not found with id 100"));
    }
    
    @Test
    @Order(8)
    public void whenPutAddresses_thenStatus404() throws Exception {
    	addressRequest.setAddress("Avenida Brasília");
    	addressRequest.setClientId(1l);
        mockMvc.perform(put("/addresses/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())           
                .andExpect(jsonPath("$.message").value("address not found with id 100"));
    }
    
    @Test
    @Order(9)
    public void whenDeleteAddresses_thenStatus404() throws Exception {
    	mockMvc.perform(delete("/addresses/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
        		.andExpect(jsonPath("$.message").value("address not found with id 100"));
    }
    
    @Test
    @Order(10)
    public void whenDeleteAddresses_thenStatus204() throws Exception {
    	mockMvc.perform(delete("/addresses/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());              
    }
}
