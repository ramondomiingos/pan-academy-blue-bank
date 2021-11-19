package com.panacademy.squad7.bluebank.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panacademy.squad7.bluebank.domain.models.Address;
import com.panacademy.squad7.bluebank.domain.models.TransactionType;
import com.panacademy.squad7.bluebank.domain.repositories.AddressesRepository;
import com.panacademy.squad7.bluebank.domain.repositories.TransactionsTypesRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;

@Service
public class TransactionsTypesServiceImpl implements TransactionsTypesService {

	private final TransactionsTypesRepository transactionsTypesRepository;
	
	public TransactionsTypesServiceImpl(TransactionsTypesRepository transactionsTypesRepository) {
        this.transactionsTypesRepository = transactionsTypesRepository;
    }

    @Override
    public TransactionType create(TransactionType transactionType) {
        return transactionsTypesRepository.save(transactionType);
    }

    @Override
    public TransactionType update(TransactionType transactionType, Integer id) {
        return transactionsTypesRepository.findById(id).map(a -> {
            transactionType.setId(id);
            return transactionsTypesRepository.save(transactionType);
        }).orElseThrow(() -> new ContentNotFoundException("address not found with id " + id));
    }

    
    @Override
    public TransactionType findById(Integer id) {
        return transactionsTypesRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("address not found with id " + id));
    }

    @Override
    public List<TransactionType> findAll() {
        return transactionsTypesRepository.findAll();
    }
}
