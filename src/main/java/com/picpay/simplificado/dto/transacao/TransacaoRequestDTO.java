package com.picpay.simplificado.dto.transacao;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransacaoRequestDTO{
    @NotNull
    @DecimalMin("0.01") 
    private BigDecimal value;
     
    @NotNull
    private Long senderId;
    
    @NotNull
    private Long recipientId;
};