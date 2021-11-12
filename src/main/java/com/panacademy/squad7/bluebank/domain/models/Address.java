package com.panacademy.squad7.bluebank.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String address;

    @Column(name = "address_number")
    private String addressNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;


}
