package com.panacademy.squad7.bluebank.domain.repositories;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAgencyNumberAndAccountNumberAndType(Long agencyNumber, Long accountNumber , AccountType type);

    @Query(value = "SELECT max(a.accountNumber) FROM Account a WHERE a.agencyNumber = :agencyNumber AND type = :type")
    Optional<Long> findMaxAccountNumberByAgencyNumberAndType(@Param("agencyNumber") Long agencyNumber, @Param("type") AccountType type);
}
