package com.ainura.finance_tracker.item.mapper;

import com.ainura.finance_tracker.item.model.dto.ItemResponse;
import com.ainura.finance_tracker.item.model.entity.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemResponse toResponse(ItemEntity itemEntity);
}
