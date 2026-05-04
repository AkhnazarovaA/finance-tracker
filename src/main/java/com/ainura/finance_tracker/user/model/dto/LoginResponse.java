package com.ainura.finance_tracker.user.model.dto;

public record LoginResponse(
        String token,
        String tokenType
) {
}
