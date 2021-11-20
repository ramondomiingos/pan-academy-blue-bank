package com.panacademy.squad7.bluebank.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.domain.repositories.ClientsRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;


@Service
public class ClientsServiceImpl implements ClientsService {
	
	private final ClientsRepository clientsRepository;

    public ClientsServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public Client create(Client client) {
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
    public void delete(Long id) {
        if (clientsRepository.existsById(id)) {
            clientsRepository.deleteById(id);
        } else {
            throw new ContentNotFoundException("client not found with id " + id);
        }
    }

    @Override
    public Client findById(Long id) {
        return clientsRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("client not found with id " + id));
    }

    @Override
    public List<Client> findAll() {
        return clientsRepository.findAll();
    }

}
