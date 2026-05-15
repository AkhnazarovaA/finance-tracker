package com.ainura.finance_tracker.item.model.dto;

import java.math.BigDecimal;

public record ItemCreateRequest (
                                 String name,
                                 BigDecimal amount,
                                 Integer quantity){
}