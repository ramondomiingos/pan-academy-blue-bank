package com.panacademy.squad7.bluebank.domain.repositories;

import com.panacademy.squad7.bluebank.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAgencyNumberAndAccountNumber(Long agencyNumber, Long accountNumber);
}
