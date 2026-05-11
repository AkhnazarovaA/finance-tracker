package com.ainura.finance_tracker.transaction.model.dto.request;

import com.ainura.finance_tracker.transaction.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionCreateRequest(
        @NotNull(message = "Transaction type is required")
        TransactionType transactionType,

        @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
        @NotNull(message = "Amount is required")
        BigDecimal amount,

        @NotBlank(message = "Category cannot be empty")
        String category,

        String description,

        @NotNull(message = "Date is required")
        @PastOrPresent(message = "You cannot record a transaction for a future date")
        LocalDate transactionDate) {
}
