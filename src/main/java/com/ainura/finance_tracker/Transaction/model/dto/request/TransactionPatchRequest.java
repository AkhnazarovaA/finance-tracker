package com.ainura.finance_tracker.transaction.model.dto.request;

import com.ainura.finance_tracker.transaction.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionPatchRequest(
        TransactionType transactionType,
        BigDecimal amount,
        String category,
        String description,
        LocalDate transactionDate) {
}
