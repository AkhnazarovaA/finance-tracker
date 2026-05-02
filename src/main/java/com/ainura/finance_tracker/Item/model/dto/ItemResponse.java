package com.ainura.finance_tracker.Item.model.dto;

import java.math.BigDecimal;

public class ItemResponse {

    private Long id;
    private String name;
    private BigDecimal amount;
    private Integer quantity;
    private Long transactionId;

    public ItemResponse(Long id, String name, BigDecimal amount, Integer quantity, Long transactionId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
        this.transactionId = transactionId;
    }
    public ItemResponse() {}

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public Long getTransactionId() {
        return transactionId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

}
