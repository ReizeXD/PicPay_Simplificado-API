package com.picpay.simplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.picpay.simplificado.entity.User;
import com.picpay.simplificado.repository.UserRepository;

import jakarta.transaction.Transactional;

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
    
    @Transactional
    public User update(Long id, User updateUser){
        
        return userRepository.findById(id).map(existingUser ->{
            existingUser.setName(updateUser.getName());
            existingUser.setEmail(updateUser.getEmail());
            
            if(updateUser.getPassword()!= null && !updateUser.getPassword().isEmpty()){
                existingUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            }
            if(updateUser.getIsMerchant()!=null){
                existingUser.setIsMerchant(updateUser.getIsMerchant());
            }
            return userRepository.save(existingUser);
        }).orElseThrow(()-> new RuntimeException("Usuario não encontrado com o ID: "+ id));
    }
    
}
