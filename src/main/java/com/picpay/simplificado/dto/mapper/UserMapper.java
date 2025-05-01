package com.picpay.simplificado.dto.mapper;

import com.picpay.simplificado.dto.UserResponseDTO;
import com.picpay.simplificado.entity.User;

public class UserMapper {
    public static UserResponseDTO toDto(User user){
        return new UserResponseDTO(user.getId(), user.getName(),user.getEmail(), user.getCpf(), user.getIsMerchant(), user.getWallet().getBalance());
    }
}
