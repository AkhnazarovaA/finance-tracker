package com.ainura.finance_tracker.Transaction.mapper;

import com.ainura.finance_tracker.Transaction.model.dto.request.TransactionRequest;
import com.ainura.finance_tracker.Transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.Transaction.model.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionEntity toEntity(TransactionRequest request);

    TransactionResponse toResponse(TransactionEntity transactionEntity);

    void updateEntityFromRequest(TransactionRequest request, @MappingTarget TransactionEntity entity);
}
