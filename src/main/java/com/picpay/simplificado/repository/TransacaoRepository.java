package com.picpay.simplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.simplificado.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
    
}
