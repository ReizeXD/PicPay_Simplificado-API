package com.picpay.simplificado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.simplificado.dto.mapper.Mapper;
import com.picpay.simplificado.dto.transacao.TransacaoRequestDTO;
import com.picpay.simplificado.dto.transacao.TransacaoResponseDTO;
import com.picpay.simplificado.entity.Transacao;
import com.picpay.simplificado.service.TransacaoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transfer")
public class TransacaoController {
    
    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody @Valid TransacaoRequestDTO transacaoRequestDTO) {
        try {
            Transacao newTransacao = transacaoService.transferir(transacaoRequestDTO);
            TransacaoResponseDTO transacaoResponseDTO = Mapper.toTransacaoDto(newTransacao);
            return ResponseEntity.ok("Transação realizada com sucesso" + transacaoResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
