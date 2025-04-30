package com.picpay.simplificado.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpay.simplificado.entity.Transacao;
import com.picpay.simplificado.entity.User;
import com.picpay.simplificado.repository.TransacaoRepository;
import com.picpay.simplificado.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransacaoService {
    
    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Transactional
    public Transacao transferir(Transacao transacao){
        //Obtendo remetente e destinatário
        User sender = userRepository.findById(transacao.getSender().getId()).orElseThrow(() -> new RuntimeException("Remetente não encontrado"));
        User recipient = userRepository.findById(transacao.getRecipient().getId()).orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        //Não pode tranferir para si
        if(sender.equals(recipient)) throw new RuntimeException("Você não pode transferir para si mesmo!");
        
        //Comerciante mão pode transferir
        if(sender.getIsMerchant()) throw new RuntimeException("Comerciante não pode transferir");
        
        //Obtendo saldos do remetente e destinatário
        BigDecimal recipientBalance = recipient.getWallet().getBalance();
        BigDecimal senderBalance = sender.getWallet().getBalance();

        //Verificando se o saldo do remetente é suficiente
        if(senderBalance.compareTo(transacao.getValue()) < 0) throw new RuntimeException("Saldo insuficiente!");

        //Mockando a autorização
        ResponseEntity<String> authResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", String.class);
        if(authResponse.getStatusCode().is2xxSuccessful()){
            String responseBody = authResponse.getBody();

            if(responseBody.contains("\"authorization\": false")) throw new RuntimeException("Transação não autorizada");
        } else throw new RuntimeException("Transação não autorizada");
        
        //Fazendo a transação em si
        walletService.setBalance(transacao.getRecipient().getId(), recipientBalance.add(transacao.getValue())); //adicionar valor nacarteira
        walletService.setBalance(transacao.getSender().getId(), senderBalance.subtract(transacao.getValue())); // tirar valor na carteira
        
        //Salvando a transação no banco
        transacao.setSender(sender);
        transacao.setRecipient(recipient);
        Transacao savedTransacao = transacaoRepository.save(transacao);

        return savedTransacao;

    }

}
