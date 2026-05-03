package com.ainura.finance_tracker.user.service;

import com.ainura.finance_tracker.user.model.dto.RegisterRequest;
import com.ainura.finance_tracker.user.model.dto.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);

}
