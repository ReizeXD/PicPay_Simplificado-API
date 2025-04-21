package com.picpay.simplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.picpay.simplificado.entity.User;
import com.picpay.simplificado.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getIsMerchant()==null){
            user.setIsMerchant(false);
        }

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email ou CPF ja cadastrado");
        }
    }

}
