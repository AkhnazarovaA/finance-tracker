package com.ainura.finance_tracker.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Name is required")
        String username,

        @NotBlank(message = "Password is required")
        String password) {
}