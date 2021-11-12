package com.panacademy.squad7.bluebank.domain.models;

import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false, name = "mother_name")
    private String motherName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String cellphone;

    @Column(nullable = false, unique = true)
    private String registration;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientType type;

    public String getType() {
        return type.getDescription();
    }
}
