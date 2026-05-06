package com.ainura.finance_tracker.Transaction.service.impl;

import com.ainura.finance_tracker.Transaction.mapper.TransactionMapper;
import com.ainura.finance_tracker.Transaction.model.dto.expense.ExpenseByCategory;
import com.ainura.finance_tracker.Transaction.model.dto.expense.TotalExpenseResponse;
import com.ainura.finance_tracker.Transaction.model.dto.income.TotalIncomeResponse;
import com.ainura.finance_tracker.Transaction.model.dto.request.TransactionRequest;
import com.ainura.finance_tracker.Transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.Transaction.model.entity.TransactionEntity;
import com.ainura.finance_tracker.Transaction.repository.TransactionRepository;
import com.ainura.finance_tracker.Transaction.service.TransactionService;
import com.ainura.finance_tracker.common.MessageResponse;
import com.ainura.finance_tracker.exception.TransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request) {
        TransactionEntity transactionEntity = mapper.toEntity(request);
        TransactionEntity savedTransactionEntity = transactionRepository.save(transactionEntity);
        return mapper.toResponse(savedTransactionEntity);
    }

    @Override
    public Page<TransactionResponse> getAll(Pageable pageable) {
        return transactionRepository.findAll(pageable)
                .map(mapper::toResponse);

    }

    @Override
    public TransactionResponse getTransactionById(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new TransactionException("Transaction not found with id: " + id));
        return mapper.toResponse(transactionEntity);
    }

    @Override
    @Transactional
    public TransactionResponse updateTransaction(Long id, TransactionRequest request) {
        TransactionEntity transactionEntity = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionException("Transaction not found with id:  + id"));

        mapper.updateEntityFromRequest(request, transactionEntity);

        TransactionEntity updated = transactionRepository.save(transactionEntity);
        return mapper.toResponse(updated);

    }

    @Override
    //TODO: RESPONSE TYPE SHOULD EXIST
    public void patchTransaction(Long id, TransactionRequest request) {
        TransactionEntity transactionEntity = findById(id, request);

        if (request.getTransactionType() != null) {
            transactionEntity.setTransactionType(request.getTransactionType());
        }
        if (request.getAmount() != null) {
            transactionEntity.setAmount(request.getAmount());
        }
        if (request.getCategory() != null) {
            transactionEntity.setCategory(request.getCategory());
        }
        if (request.getDescription() != null) {
            transactionEntity.setDescription(request.getDescription());
        }
        if (request.getTransactionDate() != null) {
            transactionEntity.setTransactionDate(request.getTransactionDate());
        }
        transactionRepository.save(transactionEntity);

    }

    @Override
    public TotalExpenseResponse getTotalExpense() {
        BigDecimal totalAmount = transactionRepository.getTotalExpense();
        return new TotalExpenseResponse(totalAmount);
    }

    @Override
    public TotalIncomeResponse getTotalIncome() {
        BigDecimal totalAmount = transactionRepository.getTotalIncome();
        return new TotalIncomeResponse(totalAmount);
    }

    @Override
    public List<ExpenseByCategory> getExpenseByCategory() {
        return transactionRepository.getExpenseByCategory();
    }

    @Override
    public MessageResponse deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new TransactionException("Transaction not found");
        }
        transactionRepository.deleteById(id);
        return new MessageResponse("Transaction deleted successfully", id);
    }

    private TransactionEntity findById(Long id, TransactionRequest request) {
        TransactionEntity transactionEntity;
        return transactionEntity = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new TransactionException("Transaction not found with id: " + id));
    }

}
