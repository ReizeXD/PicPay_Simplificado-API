package com.picpay.simplificado.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.simplificado.entity.User;
import com.picpay.simplificado.repository.UserRepository;
import com.picpay.simplificado.repository.WalletRepository;

@Service
public class WalletService {
    
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    public BigDecimal getBalance(Long userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if(user.getWallet() == null) throw new RuntimeException("Usuario não possui carteira");

        BigDecimal userBalance = user.getWallet().getBalance();
        return userBalance;
            
    }
}
