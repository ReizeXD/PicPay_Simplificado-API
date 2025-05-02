package com.picpay.simplificado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.simplificado.dto.mapper.Mapper;
import com.picpay.simplificado.dto.user.UserRequestDTO;
import com.picpay.simplificado.dto.user.UserResponseDTO;
import com.picpay.simplificado.dto.user.UserUpdateDTO;
import com.picpay.simplificado.entity.User;
import com.picpay.simplificado.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid UserRequestDTO userRequestDTO){
        try {
            User newUser=this.userService.save(userRequestDTO);
            UserResponseDTO userResponseDTO = Mapper.toUserDto(newUser);
            return ResponseEntity.status(201).body(userResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            User updateUser=this.userService.update(id, userUpdateDTO);
            UserResponseDTO userResponseDTO = Mapper.toUserDto(updateUser);
            return ResponseEntity.status(201).body(userResponseDTO); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
