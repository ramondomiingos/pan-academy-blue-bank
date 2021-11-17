package com.panacademy.squad7.bluebank.domain.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "agency_number", "account_number" }) })
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agency_number", nullable = false)
    private Long agencyNumber;

    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @Column(name = "account_digit", nullable = false)
    private Character accountDigit;

    @Column(nullable = false, columnDefinition = "decimal(10, 2) default 0")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "originAccount")
    private List<Transaction> madeTransfers;

    @OneToMany(mappedBy = "destinationAccount")
    private List<Transaction> receivedTransfers;

    public String getType() {
        return type.getDescription();
    }

    public String getStatus() {
        return status.getDescription();
    }

}
