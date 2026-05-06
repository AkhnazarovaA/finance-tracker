package com.ainura.finance_tracker.auth.service;

import com.ainura.finance_tracker.auth.dto.AuthResponse;
import com.ainura.finance_tracker.auth.dto.LoginRequest;
import com.ainura.finance_tracker.auth.dto.RegisterRequest;
import com.ainura.finance_tracker.exception.UserException;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import com.ainura.finance_tracker.user.model.enums.UserRole;
import com.ainura.finance_tracker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserException("User with this username already exists");
        }

        UserEntity user = UserEntity.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .userRole(UserRole.USER)
                .build();
        userRepository.save(user);
        return new AuthResponse(jwtService.generateToken(user));
    }


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserEntity user = userRepository.findByUsername(request.username()).orElseThrow();
        return new AuthResponse(jwtService.generateToken(user));
    }
}