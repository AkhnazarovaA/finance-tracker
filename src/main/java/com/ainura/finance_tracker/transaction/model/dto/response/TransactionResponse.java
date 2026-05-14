package com.ainura.finance_tracker.transaction.model.dto.response;

import com.ainura.finance_tracker.transaction.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public record TransactionResponse(
        Long id,
        TransactionType transactionType,
        BigDecimal amount,
        String category,
        String description,
        LocalDate transactionDate,
        LocalDateTime createdAt) {

}
