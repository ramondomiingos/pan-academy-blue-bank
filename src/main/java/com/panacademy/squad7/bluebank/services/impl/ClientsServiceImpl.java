package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.domain.repositories.AccountsRepository;
import com.panacademy.squad7.bluebank.domain.repositories.ClientsRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;
import com.panacademy.squad7.bluebank.services.ClientsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientsServiceImpl implements ClientsService {
    public static final String NOT_FOUND_MESSAGE = "client not found with id ";
    private final ClientsRepository clientsRepository;
    private final AccountsRepository accountsRepository;

    public ClientsServiceImpl(ClientsRepository clientsRepository, AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
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
        }).orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    public void softDelete(Long id) {
        Optional<Client> client = clientsRepository.findById(id);
        if (client.isPresent()) {
            Client c = client.get();
            c.setStatus(StatusType.C);
            List<Account> accounts = c.getAccounts();
            accounts.forEach(account -> {
                account.setStatus(StatusType.C);
                accountsRepository.save(account);
            });
            clientsRepository.save(c);
        } else {
            throw new ContentNotFoundException(NOT_FOUND_MESSAGE + id);
        }
    }

    @Override
    public void softBlock(Long id) {
        Optional<Client> client = clientsRepository.findById(id);
        if (client.isPresent()) {
            Client c = client.get();
            c.setStatus(StatusType.B);
            clientsRepository.save(c);
        } else {
            throw new ContentNotFoundException(NOT_FOUND_MESSAGE + id);
        }
    }

    @Override
    public Client findById(Long id) {
        return clientsRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<Client> findAll() {
        return clientsRepository.findAll().stream().filter(client -> client.getStatus().equals(StatusType.A))
                .collect(Collectors.toList());
    }

}
