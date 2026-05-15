package com.ainura.finance_tracker.user.mapper;

import com.ainura.finance_tracker.user.model.dto.UserResponse;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(UserEntity userEntity);
}
