package com.ainura.finance_tracker.user.service;

import com.ainura.finance_tracker.user.model.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

}
