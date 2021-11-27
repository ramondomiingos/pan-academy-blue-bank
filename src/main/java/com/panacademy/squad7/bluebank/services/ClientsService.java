package com.panacademy.squad7.bluebank.services;

import java.util.List;

import com.panacademy.squad7.bluebank.domain.models.Client;

public interface ClientsService {

	Client create(Client client);

    Client update(Client client, Long id);

    void delete(Long id);
    
    Client findById(Long id);

    List<Client> findAll();
}
