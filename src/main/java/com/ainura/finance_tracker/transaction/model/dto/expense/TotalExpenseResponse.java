package com.ainura.finance_tracker.transaction.model.dto.expense;

import java.math.BigDecimal;

public class TotalExpenseResponse {
    private BigDecimal totalExpense;
    public TotalExpenseResponse(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }
    public BigDecimal getTotalExpense() {
        return totalExpense;
    }
}
