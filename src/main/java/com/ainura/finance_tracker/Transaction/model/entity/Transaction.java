package com.ainura.finance_tracker.Transaction.model.entity;

import com.ainura.finance_tracker.Item.model.entity.Item;
import com.ainura.finance_tracker.Transaction.enums.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String category;

    private String description;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

//    @OneToMany(mappedBy = "transaction")
//    private List<Item> items;

    public Transaction() {}   //This is an empty constructor without any arguments
                              // it will CREATE transaction object

    public Transaction(TransactionType transactionType, BigDecimal amount, String category, String description, LocalDate transactionDate, List<Item> items) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.transactionDate = transactionDate;
//        this.items = items;
    }

    public Long getId() {
        return id;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
//    public List<Item> getItems() {
//        return items;
//    }
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }
}
