package com.ainura.finance_tracker.user.service.impl;

import com.ainura.finance_tracker.user.mapper.UserMapper;
import com.ainura.finance_tracker.user.model.dto.UserResponse;
import com.ainura.finance_tracker.user.repository.UserRepository;
import com.ainura.finance_tracker.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(mapper::toResponse);

    }
}
