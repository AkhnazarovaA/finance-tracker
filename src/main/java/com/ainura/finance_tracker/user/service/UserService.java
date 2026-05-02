package com.ainura.finance_tracker.user.service;

import com.ainura.finance_tracker.user.model.dto.RegisterRequest;
import com.ainura.finance_tracker.user.model.dto.UserCreateResponse;
import com.ainura.finance_tracker.user.model.entity.User;

public interface UserService {

    UserCreateResponse registerUser(RegisterRequest request);

}
