package com.ainura.finance_tracker.item.mapper;

import com.ainura.finance_tracker.item.model.dto.ItemResponse;
import com.ainura.finance_tracker.item.model.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "transactionId", source = "transaction.id")
    ItemResponse toResponse(ItemEntity entity);
}
