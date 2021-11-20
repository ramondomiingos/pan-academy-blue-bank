package com.panacademy.squad7.bluebank.services;

import java.util.List;

import com.panacademy.squad7.bluebank.domain.models.Transaction;

public interface TransactionsService {

	Transaction create(Transaction transaction);
	
	Transaction findById(Long id);
	
	List<Transaction> findAll();
	
}
