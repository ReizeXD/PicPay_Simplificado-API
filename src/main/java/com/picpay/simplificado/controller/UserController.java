package com.picpay.simplificado.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> save(@RequestBody @Valid User user){
        try {
            User newUser=this.userService.save(user);
            newUser.setPassword(null);
            return ResponseEntity.status(201).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user) {
        try {
            User updateUser=this.userService.update(id, user);
            return ResponseEntity.status(201).body(updateUser); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
