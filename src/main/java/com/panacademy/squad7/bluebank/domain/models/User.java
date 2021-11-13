package com.panacademy.squad7.bluebank.domain.models;

import com.panacademy.squad7.bluebank.domain.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name= "users")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name="client_id")
    private Integer clientId ;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;







}
