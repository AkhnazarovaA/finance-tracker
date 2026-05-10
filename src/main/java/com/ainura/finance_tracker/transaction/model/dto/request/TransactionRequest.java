package com.ainura.finance_tracker.transaction.model.dto.request;

import com.ainura.finance_tracker.transaction.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

//Post and Put data
public class TransactionRequest {

    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    private String description;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "You cannot record a transaction for a future date")
    private LocalDate transactionDate;

    public TransactionType getTransactionType() {
        return transactionType;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setCategory(String category) {
        this.category = category;
    }
     public void setDescription(String description) {
        this.description = description;
     }
     public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
     }
}
