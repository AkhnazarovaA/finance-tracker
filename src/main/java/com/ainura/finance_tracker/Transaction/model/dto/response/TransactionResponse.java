package com.ainura.finance_tracker.Transaction.model.dto.response;

import com.ainura.finance_tracker.Transaction.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;


public record TransactionResponse(
        TransactionType transactionType,
        BigDecimal amount,
        String category,
        String description,
        LocalDate transactionDate) {

}
