package com.picpay.simplificado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.simplificado.entity.Transacao;
import com.picpay.simplificado.service.TransacaoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transfer")
public class TransacaoController {
    
    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody @Valid Transacao transacao) {
        try {
            Transacao newTransacao = transacaoService.transferir(transacao);
            return ResponseEntity.ok("Transação realizada com sucesso" + newTransacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
