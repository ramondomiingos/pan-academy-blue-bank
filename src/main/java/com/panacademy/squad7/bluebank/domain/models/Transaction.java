package com.panacademy.squad7.bluebank.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="origin_account")
    private Integer originAccount;

    @Column(nullable = false, name="destination_account")
    private Integer destinationAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TransactionType type;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;


}
