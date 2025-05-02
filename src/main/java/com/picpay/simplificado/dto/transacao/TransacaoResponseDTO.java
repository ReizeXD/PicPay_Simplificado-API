package com.picpay.simplificado.dto.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoResponseDTO(
    Long id,
    LocalDateTime time,
    BigDecimal value,
    Long senderId,
    String senderName,
    Long recipientId,
    String recipientName
) {}
