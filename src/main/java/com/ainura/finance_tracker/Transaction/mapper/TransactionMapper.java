package com.ainura.finance_tracker.Transaction.mapper;

import com.ainura.finance_tracker.Transaction.model.dto.request.TransactionRequest;
import com.ainura.finance_tracker.Transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.Transaction.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toEntity(TransactionRequest request);

    TransactionResponse toResponse(Transaction transaction);

    void updateEntityFromRequest(TransactionRequest request, @MappingTarget Transaction entity);
}
