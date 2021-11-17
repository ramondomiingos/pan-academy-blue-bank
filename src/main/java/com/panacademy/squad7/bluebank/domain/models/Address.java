package com.panacademy.squad7.bluebank.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(name = "address_number")
    private String addressNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
