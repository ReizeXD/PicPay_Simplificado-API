package com.picpay.simplificado.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;   

    @NotBlank
    private String cpf;
    private String password;
    private Boolean isMerchant;
}
