package com.ainura.finance_tracker.user.model.dto;

public record LoginRequest(
        String email,
        String password
) {
}
