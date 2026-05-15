package com.ainura.finance_tracker.transaction.model.dto.expense;

import java.math.BigDecimal;

public record ExpenseByCategory(String category, BigDecimal totalAmount) {
}
