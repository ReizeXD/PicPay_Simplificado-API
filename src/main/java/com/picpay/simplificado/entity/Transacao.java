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
import jakarta.validation.constraints.NotNull;
import lombok.Data;


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
    @NotNull(message = "A data não pode ser nulo")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;
    
    @ManyToOne
    @JoinColumn(name="recipient_id")
    private User recipient;
}
