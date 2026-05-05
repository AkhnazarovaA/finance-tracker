package com.ainura.finance_tracker.auth.dto;

public record RegisterRequest(String userName, String email, String password) {
}