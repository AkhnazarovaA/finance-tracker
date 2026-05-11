package com.ainura.finance_tracker.transaction.mapper;

import com.ainura.finance_tracker.transaction.model.dto.request.TransactionCreateRequest;
import com.ainura.finance_tracker.transaction.model.dto.request.TransactionUpdateRequest;
import com.ainura.finance_tracker.transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.transaction.model.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionEntity toEntity(TransactionCreateRequest request);

    TransactionResponse toResponse(TransactionEntity transactionEntity);

    void updateEntityFromRequest(TransactionUpdateRequest request, @MappingTarget TransactionEntity entity);
}
