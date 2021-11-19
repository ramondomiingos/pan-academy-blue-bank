package com.panacademy.squad7.bluebank.services;

import java.util.List;

import com.panacademy.squad7.bluebank.domain.models.TransactionType;

public interface TransactionsTypesService {

	TransactionType create(TransactionType transactionType);
	
	TransactionType update(TransactionType transactionType, Integer id);
		
	TransactionType findById(Integer id);
	
	List<TransactionType> findAll();
}
