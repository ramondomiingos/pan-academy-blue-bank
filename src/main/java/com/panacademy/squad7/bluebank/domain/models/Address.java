package com.panacademy.squad7.bluebank.domain.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(name = "address_number")
    private String addressNumber;

    @Column
    private String details;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false, length = 8)
    private String zip;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
