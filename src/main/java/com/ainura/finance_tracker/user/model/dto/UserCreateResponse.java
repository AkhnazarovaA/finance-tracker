package com.ainura.finance_tracker.user.model.dto;

import com.ainura.finance_tracker.user.model.enums.UserRole;
import lombok.Data;

@Data
public class UserCreateResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
}
