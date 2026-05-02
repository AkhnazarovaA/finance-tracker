package com.ainura.finance_tracker.Transaction.model.dto.expense;

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
