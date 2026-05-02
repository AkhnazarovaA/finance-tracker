package com.ainura.finance_tracker.Item.model.entity;

import com.ainura.finance_tracker.Transaction.model.entity.Transaction;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    private Transaction transaction;

    public Item() {}

    public Item(Long id, String name, BigDecimal amount, Integer quantity, Transaction transaction) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
        this.transaction = transaction;
    }
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
    public Transaction getTransaction() {
        return transaction;
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
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
