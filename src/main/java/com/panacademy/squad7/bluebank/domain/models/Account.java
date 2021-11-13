package com.panacademy.squad7.bluebank.domain.models;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name ="accounts" )
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "account_number", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountNumber;

    @Column(name="agency_number", nullable = false)
    private Integer agencyNumber;

    @Column(name="account_digit", nullable = false)
    private Character accountDigit;

    @Column(nullable = false, columnDefinition = "double default 0")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType status;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client clientId;


    public String getType() {
        return type.getDescription();
    }










}
