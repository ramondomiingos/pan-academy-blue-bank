package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.domain.repositories.ClientsRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;
import com.panacademy.squad7.bluebank.services.ClientsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;

    public ClientsServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public Client create(Client client) {
        client.setStatus(StatusType.A);
        return clientsRepository.save(client);
    }

    @Override
    public Client update(Client client, Long id) {
        return clientsRepository.findById(id).map(c -> {
            client.setId(id);
            client.setRegistration(c.getRegistration());
            return clientsRepository.save(client);
        }).orElseThrow(() -> new ContentNotFoundException("client not found with id " + id));
    }

    @Override
    public void softDelete(Long id) {
        clientsRepository.findById(id).map(account -> {
            account.setStatus(StatusType.C);
            return clientsRepository.save(account);
        }).orElseThrow(() -> new ContentNotFoundException("client not found with id " + id));
    }

    @Override
    public void softBlock(Long id) {
        clientsRepository.findById(id).map(account -> {
            account.setStatus(StatusType.B);
            return clientsRepository.save(account);
        }).orElseThrow(() -> new ContentNotFoundException("client not found with id " + id));
    }

    @Override
    public Client findById(Long id) {
        return clientsRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("client not found with id " + id));
    }

    @Override
    public List<Client> findAll() {
        return clientsRepository.findAll().stream().filter(client -> client.getStatus().equals(StatusType.A))
                .collect(Collectors.toList());
    }

}
