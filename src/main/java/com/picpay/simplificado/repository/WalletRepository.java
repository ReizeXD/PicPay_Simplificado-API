package com.picpay.simplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.simplificado.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
}
