package com.ainura.finance_tracker.Transaction.model.dto.income;

import java.math.BigDecimal;

public class TotalIncomeResponse {

    private BigDecimal totalIncome;

    public TotalIncomeResponse(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }
    public BigDecimal getTotalIncome() {
        return totalIncome;
    }
}
