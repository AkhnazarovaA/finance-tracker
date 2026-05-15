package com.ainura.finance_tracker.user.service.impl;

import com.ainura.finance_tracker.exception.UserException;
import com.ainura.finance_tracker.user.mapper.UserMapper;
import com.ainura.finance_tracker.user.model.dto.UserResponse;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import com.ainura.finance_tracker.user.repository.UserRepository;
import com.ainura.finance_tracker.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(mapper::toResponse);

    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
