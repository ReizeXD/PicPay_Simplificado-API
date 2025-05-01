package com.picpay.simplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.picpay.simplificado.dto.UserRequestDTO;
import com.picpay.simplificado.dto.UserResponseDTO;
import com.picpay.simplificado.dto.UserUpdateDTO;
import com.picpay.simplificado.entity.User;
import com.picpay.simplificado.entity.Wallet;
import com.picpay.simplificado.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User save(UserRequestDTO userUpdateDTO){
        //Convertendo DTO
        User user = new User();
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        user.setName(userUpdateDTO.getName());
        user.setCpf(userUpdateDTO.getCpf());
        user.setEmail(userUpdateDTO.getEmail());
        user.setIsMerchant(userUpdateDTO.getIsMerchant());


        if(user.getIsMerchant()==null){
            user.setIsMerchant(false);
        }
        
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        user.setWallet(wallet);

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email ou CPF ja cadastrado");
        }
    }
    
    @Transactional
    public User update(Long id, UserUpdateDTO userUpdateDTO){
        
        return userRepository.findById(id).map(existingUser ->{
            if(userUpdateDTO.getEmail()!=null && !userUpdateDTO.getEmail().isEmpty()){
                existingUser.setEmail(userUpdateDTO.getEmail());
            }
            if(userUpdateDTO.getName()!=null && !userUpdateDTO.getName().isEmpty()){
                existingUser.setName(userUpdateDTO.getName());
            }
            if(userUpdateDTO.getPassword()!= null && !userUpdateDTO.getPassword().isEmpty()){
                existingUser.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            }
            if(userUpdateDTO.getIsMerchant()!=null ){
                existingUser.setIsMerchant(userUpdateDTO.getIsMerchant());
            }
            return userRepository.save(existingUser);
        }).orElseThrow(()-> new EntityNotFoundException("Usuario não encontrado com o ID: "+ id));
    }
    

}
