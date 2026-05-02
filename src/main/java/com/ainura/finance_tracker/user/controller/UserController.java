package com.ainura.finance_tracker.user.controller;

import com.ainura.finance_tracker.user.model.dto.RegisterRequest;
import com.ainura.finance_tracker.user.model.dto.UserCreateRequest;
import com.ainura.finance_tracker.user.model.dto.UserCreateResponse;
import com.ainura.finance_tracker.user.model.entity.User;
import com.ainura.finance_tracker.user.repository.UserRepository;
import com.ainura.finance_tracker.user.service.UserService;
import com.ainura.finance_tracker.user.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserCreateResponse registerUser(@Valid @RequestBody RegisterRequest request) {
       return userService.registerUser(request);
    }
}
