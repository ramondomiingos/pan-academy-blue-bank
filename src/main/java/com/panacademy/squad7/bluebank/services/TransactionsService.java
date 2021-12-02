package com.panacademy.squad7.bluebank.services;

import com.panacademy.squad7.bluebank.domain.models.Transaction;

import java.util.List;

public interface TransactionsService {

    Transaction deposit(Transaction transaction);

    Transaction withdraw(Transaction transaction);

    Transaction transfer(Transaction transaction);

    Transaction findById(Long id);

    List<Transaction> findAll();

}
