package com.picpay.simplificado.dto.mapper;

import com.picpay.simplificado.dto.transacao.TransacaoResponseDTO;
import com.picpay.simplificado.dto.user.UserResponseDTO;
import com.picpay.simplificado.entity.Transacao;
import com.picpay.simplificado.entity.User;

public class Mapper {
    public static UserResponseDTO toUserDto(User user){
        return new UserResponseDTO(user.getId(), user.getName(),user.getEmail(), user.getCpf(), user.getIsMerchant(), user.getWallet().getBalance());
    }
    public static TransacaoResponseDTO toTransacaoDto(Transacao transacao){
        return new TransacaoResponseDTO(transacao.getId(), transacao.getTime(),transacao.getValue(),transacao.getSender().getId(), transacao.getSender().getName(),transacao.getRecipient().getId(), transacao.getRecipient().getName());
    }
}
