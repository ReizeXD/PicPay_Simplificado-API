package com.picpay.simplificado.entity;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "O nome não pode ser nulo")
    private String name;
    
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email inválido")
    @NotNull(message = "O email não pode ser nulo")
    private String email;

    @Column(name = "cpf", nullable = false, unique = true)
    @NotNull(message = "O cpf não pode ser nulo")
    @Size(min = 11, max = 11, message = "A cpf deve ter 11 caracteres")
    private String cpf;

    @Column(name = "password", nullable = false)
    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    @Column(name = "is_merchant", nullable = false)
    private Boolean isMerchant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id",referencedColumnName = "id")
    private Wallet wallet;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    @ToString.Exclude
    private List<Transacao> sendTransactions = new ArrayList<>();
    @OneToMany(mappedBy = "recipient")
    @JsonIgnore
    @ToString.Exclude
    private List<Transacao> receiveTransactions = new ArrayList<>();

}
