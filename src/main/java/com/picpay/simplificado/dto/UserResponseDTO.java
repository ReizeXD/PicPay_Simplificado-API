package com.picpay.simplificado.dto;

import java.math.BigDecimal;

public record UserResponseDTO (
    Long id,
    String name,
    String email, 
    String cpf,
    Boolean isMerchant,
    BigDecimal balance
){}
