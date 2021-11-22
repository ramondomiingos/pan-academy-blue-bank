package com.panacademy.squad7.bluebank.domain.models;

import com.panacademy.squad7.bluebank.domain.enums.TransactionTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "transaction_type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionTypes type;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Transaction> transactions;    
}
