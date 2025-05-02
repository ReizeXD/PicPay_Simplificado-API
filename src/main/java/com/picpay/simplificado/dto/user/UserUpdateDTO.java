package com.picpay.simplificado.dto.user;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String email;
    private String cpf;
    private String password;
    private Boolean isMerchant;
}
