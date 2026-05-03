package com.ainura.finance_tracker.user.mapper;

import com.ainura.finance_tracker.user.model.dto.RegisterRequest;
import com.ainura.finance_tracker.user.model.dto.UserResponse;
import com.ainura.finance_tracker.user.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);
    UserResponse toResponse(User user);
}
