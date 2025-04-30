package com.picpay.simplificado.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.ToString;


@Data
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    @DecimalMin("0.01")
    private BigDecimal value;
    
    @CreationTimestamp 
    @Column(name = "time", updatable = false)
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name="sender_id")
    @ToString.Exclude
    private User sender;
    
    @ManyToOne
    @JoinColumn(name="recipient_id")
    @ToString.Exclude
    private User recipient;
}
