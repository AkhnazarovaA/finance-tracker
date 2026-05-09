package com.ainura.finance_tracker.transaction.model.dto.expense;

import java.math.BigDecimal;

public class ExpenseByCategory {
    private String category;
    private BigDecimal totalAmount;

    public ExpenseByCategory(String category, BigDecimal totalAmount) {
        this.category = category;
        this.totalAmount = totalAmount;
    }
    public String getCategory() {
       return category;
   }
   public BigDecimal getTotalAmount() {
       return totalAmount;
   }
}
