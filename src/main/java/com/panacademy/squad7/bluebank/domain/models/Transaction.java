package com.panacademy.squad7.bluebank.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {
	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(nullable = false)
	private Integer originAccount;
    
    @Column(nullable = false)
	private Integer destinationAccount;
    
    @Column(nullable = false)
	private BigDecimal amount;
        
    @Column(nullable = false)
	private TransactionType type;
    
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "transaction_type")
    private List<Transaction> transactions;
	
}
