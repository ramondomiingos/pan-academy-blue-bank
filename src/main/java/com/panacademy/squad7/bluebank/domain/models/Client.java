package com.panacademy.squad7.bluebank.domain.models;

import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "mother_name")
    private String motherName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String cellphone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType status;

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

}
