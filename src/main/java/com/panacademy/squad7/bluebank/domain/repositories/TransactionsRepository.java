package com.panacademy.squad7.bluebank.domain.repositories;

import com.panacademy.squad7.bluebank.domain.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

}
