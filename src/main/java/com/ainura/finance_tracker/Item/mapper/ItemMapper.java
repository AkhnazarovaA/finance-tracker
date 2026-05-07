package com.ainura.finance_tracker.Item.mapper;

import com.ainura.finance_tracker.Item.model.dto.ItemResponse;
import com.ainura.finance_tracker.Item.model.entity.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemResponse toResponse(ItemEntity itemEntity);
}
