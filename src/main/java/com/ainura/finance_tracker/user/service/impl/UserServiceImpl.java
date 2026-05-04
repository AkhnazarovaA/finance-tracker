package com.ainura.finance_tracker.user.service.impl;

import com.ainura.finance_tracker.exception.UserException;
import com.ainura.finance_tracker.user.mapper.UserMapper;
import com.ainura.finance_tracker.user.model.dto.RegisterRequest;
import com.ainura.finance_tracker.user.model.dto.UserResponse;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
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
    public UserResponse registerUser(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserException("User with this email already exists");
        }
        UserEntity userEntity =  mapper.toEntity(request);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setUserRole(UserRole.USER);
        UserEntity savedUserEntity = repository.save(userEntity);
        return mapper.toResponse(savedUserEntity);
    }
}
