package com.ainura.finance_tracker.user.model.dto;

import com.ainura.finance_tracker.user.model.enums.UserRole;
import lombok.Data;

public record UserResponse(
        Long id,
        String userName,
        String email,
        UserRole userRole) {

}

