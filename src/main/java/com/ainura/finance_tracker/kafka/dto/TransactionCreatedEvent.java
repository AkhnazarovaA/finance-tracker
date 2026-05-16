package com.ainura.finance_tracker.kafka.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionCreatedEvent(
        Long transactionId,
        Long userId,
        String transactionType,
        BigDecimal amount,
        String category,
        LocalDate transactionDate
) {
}
