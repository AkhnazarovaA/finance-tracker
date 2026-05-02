package com.ainura.finance_tracker.Transaction.model.dto.response;

import com.ainura.finance_tracker.Transaction.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionResponse {
    private TransactionType transactionType;
    private BigDecimal amount;
    private String category;
    private String description;
    private LocalDate transactionDate;

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

}
