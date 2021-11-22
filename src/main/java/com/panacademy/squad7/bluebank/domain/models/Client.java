package com.panacademy.squad7.bluebank.domain.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.panacademy.squad7.bluebank.domain.enums.ClientType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientType type;

    @Column(nullable = false, unique = true)
    private String registration;

    @OneToOne(mappedBy = "client")
    private Address address;

    @OneToOne(mappedBy = "client")
    private User user;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;

    public String getType() {
        return type.getDescription();
    }

}
