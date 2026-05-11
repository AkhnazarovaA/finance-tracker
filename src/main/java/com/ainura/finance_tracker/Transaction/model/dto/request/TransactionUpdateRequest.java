package com.ainura.finance_tracker.transaction.model.dto.request;

import com.ainura.finance_tracker.transaction.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionUpdateRequest(
        @NotNull(message = "Transaction type is required")
        TransactionType transactionType,

        @NotNull(message = "Transaction amount is required")
        BigDecimal amount,

        @NotBlank(message = "Transaction category is required")
        String category,

        String description,

        @NotNull(message = "Transaction date is required")
        @PastOrPresent(message = "You cannot record a transaction for a future date")
        LocalDate transactionDate
) {
}