package com.panacademy.squad7.bluebank.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panacademy.squad7.bluebank.domain.models.Transaction;
import com.panacademy.squad7.bluebank.domain.repositories.TransactionsRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;

@Service
public class TransactionsServiceImpl implements TransactionsService {
	
	private final TransactionsRepository transactionsRepository;
	
	public TransactionsServiceImpl(TransactionsRepository transactionsRepository) {
		this.transactionsRepository = transactionsRepository;
	}

	@Override
	public Transaction create(Transaction transaction) {
		return transactionsRepository.save(transaction);
	}
	
	@Override
	public Transaction update(Transaction transaction, Long id) {
		return transactionsRepository.findById(id).map(a -> {
			transaction.setId(id);
			return transactionsRepository.save(transaction);
		}).orElseThrow(() -> new ContentNotFoundException("transaction not found with id" + id));
	}
	
	@Override
	public Transaction findById(Long id) {
		return transactionsRepository.findById(id)
				.orElseThrow(() -> new ContentNotFoundException("transaction not found with id" + id));
	}
	
	@Override
	public List<Transaction> findAll() {
		return transactionsRepository.findAll();
	}
	
}
