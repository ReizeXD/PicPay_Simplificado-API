package com.picpay.simplificado.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
@Entity
public class Wallet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance", precision = 19, scale = 2)
    @DecimalMin("0")
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToOne(mappedBy = "wallet")
    @JsonIgnore
    private User user;    
    
}
