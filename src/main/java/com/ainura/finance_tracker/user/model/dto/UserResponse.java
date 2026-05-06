package com.ainura.finance_tracker.user.model.dto;

import com.ainura.finance_tracker.user.model.enums.UserRole;

public record UserResponse(
        Long id,
        String username,
        String email,
        UserRole userRole) {

}

