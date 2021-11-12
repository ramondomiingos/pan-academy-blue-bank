package com.panacademy.squad7.bluebank.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.domain.enums.TransactionTypes;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction_types")
@Getter
@Setter
public class TransactionType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
    private TransactionTypes type;

	@Column(nullable = false)
    private String name;
	
    public String getType() {
        return type.getDescription();
    }
    
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transactions;
    
}
