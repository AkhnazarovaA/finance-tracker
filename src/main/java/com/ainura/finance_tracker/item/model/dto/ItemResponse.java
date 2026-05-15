package com.ainura.finance_tracker.item.model.dto;

import java.math.BigDecimal;

public record ItemResponse(Long id,
                           String name,
                           BigDecimal amount,
                           Integer quantity,
                           Long transactionId) {
}
