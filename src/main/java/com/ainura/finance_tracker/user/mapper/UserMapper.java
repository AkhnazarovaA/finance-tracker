package com.ainura.finance_tracker.user.mapper;

import com.ainura.finance_tracker.user.model.dto.RegisterRequest;
import com.ainura.finance_tracker.user.model.dto.UserResponse;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(RegisterRequest request);
    @Mapping(source = "displayUsername", target = "userName")
    UserResponse toResponse(UserEntity userEntity);
}
