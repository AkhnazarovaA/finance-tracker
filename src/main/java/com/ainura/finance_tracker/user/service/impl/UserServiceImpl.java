package com.ainura.finance_tracker.user.service.impl;

import com.ainura.finance_tracker.exception.UserException;
import com.ainura.finance_tracker.user.mapper.UserMapper;
import com.ainura.finance_tracker.user.model.dto.RegisterRequest;
import com.ainura.finance_tracker.user.model.dto.UserCreateResponse;
import com.ainura.finance_tracker.user.model.entity.User;
import com.ainura.finance_tracker.user.model.enums.UserRole;
import com.ainura.finance_tracker.user.repository.UserRepository;
import com.ainura.finance_tracker.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public UserCreateResponse registerUser(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserException("User with this email already exists");
        }
        User user =  mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRole.USER);
        User savedUser = repository.save(user);
        return mapper.toResponse(savedUser);
    }
}
