package com.ainura.finance_tracker.user.service;

import com.ainura.finance_tracker.user.model.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    Page<UserResponse> getAll(Pageable pageable);
}
