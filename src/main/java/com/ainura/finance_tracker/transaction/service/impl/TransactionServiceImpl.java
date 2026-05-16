package com.ainura.finance_tracker.transaction.service.impl;

import com.ainura.finance_tracker.auth.service.AuthService;
import com.ainura.finance_tracker.kafka.dto.TransactionCreatedEvent;
import com.ainura.finance_tracker.kafka.service.TransactionEventProducer;
import com.ainura.finance_tracker.transaction.enums.TransactionType;
import com.ainura.finance_tracker.transaction.mapper.TransactionMapper;
import com.ainura.finance_tracker.transaction.model.dto.expense.ExpenseByCategory;
import com.ainura.finance_tracker.transaction.model.dto.expense.TotalExpenseResponse;
import com.ainura.finance_tracker.transaction.model.dto.income.TotalIncomeResponse;
import com.ainura.finance_tracker.transaction.model.dto.request.TransactionPatchRequest;
import com.ainura.finance_tracker.transaction.model.dto.request.TransactionCreateRequest;
import com.ainura.finance_tracker.transaction.model.dto.request.TransactionUpdateRequest;
import com.ainura.finance_tracker.transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.transaction.model.entity.TransactionEntity;
import com.ainura.finance_tracker.transaction.repository.TransactionRepository;
import com.ainura.finance_tracker.transaction.service.TransactionService;
import com.ainura.finance_tracker.common.MessageResponse;
import com.ainura.finance_tracker.exception.TransactionException;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
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
    private final AuthService authService;
    private final TransactionEventProducer transactionEventProducer;

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionCreateRequest request) {
        UserEntity userEntity = authService.getCurrentUser();
        TransactionEntity transactionEntity = mapper.toEntity(request);
        transactionEntity.setUser(userEntity);
        TransactionEntity saved = transactionRepository.save(transactionEntity);
        transactionEventProducer.sendTransactionCreated(
                new TransactionCreatedEvent(
                        saved.getId(),
                        saved.getUser().getId(),
                        saved.getTransactionType().name(),
                        saved.getAmount(),
                        saved.getCategory(),
                        saved.getTransactionDate()
                )
        );

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponse> getAll(Pageable pageable) {
        UserEntity userEntity = authService.getCurrentUser();
        return transactionRepository.findAllByUser(userEntity, pageable)
                .map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponse getTransactionById(Long id) {
        TransactionEntity transactionEntity = getTransactionForCurrentUser(id);
        return mapper.toResponse(transactionEntity);
    }

    @Override
    @Transactional
    public TransactionResponse updateTransaction(Long id, TransactionUpdateRequest request) {
        TransactionEntity transactionEntity = getTransactionForCurrentUser(id);
        mapper.updateEntityFromRequest(request, transactionEntity);
        TransactionEntity updated = transactionRepository.save(transactionEntity);
        return mapper.toResponse(updated);

    }

    @Override
    @Transactional
    public MessageResponse patchTransaction(Long id, TransactionPatchRequest request) {
        TransactionEntity transactionEntity = getTransactionForCurrentUser(id);

        if (request.transactionType() != null) {
            transactionEntity.setTransactionType(request.transactionType());
        }
        if (request.amount() != null) {
            transactionEntity.setAmount(request.amount());
        }
        if (request.category() != null) {
            transactionEntity.setCategory(request.category());
        }
        if (request.description() != null) {
            transactionEntity.setDescription(request.description());
        }
        if (request.transactionDate() != null) {
            transactionEntity.setTransactionDate(request.transactionDate());
        }
        transactionRepository.save(transactionEntity);
        return new MessageResponse("Transaction has been updated successfully", id);

    }

    @Override
    @Transactional(readOnly = true)
    public TotalExpenseResponse getTotalExpense() {
        UserEntity currentUser = authService.getCurrentUser();
        BigDecimal totalAmount = transactionRepository.getTotalExpense(currentUser);
        return new TotalExpenseResponse(totalAmount);
    }

    @Override
    @Transactional(readOnly = true)
    public TotalIncomeResponse getTotalIncome() {
        UserEntity currentUser = authService.getCurrentUser();
        BigDecimal totalAmount = transactionRepository.getTotalIncome(currentUser);
        return new TotalIncomeResponse(totalAmount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseByCategory> getExpenseByCategory() {
        UserEntity currentUser = authService.getCurrentUser();
        return transactionRepository.getExpenseByCategory(TransactionType.EXPENSE, currentUser);
    }

    @Override
    @Transactional
    public MessageResponse deleteTransaction(Long id) {
        TransactionEntity byIdAndUser = getTransactionForCurrentUser(id);
        transactionRepository.delete(byIdAndUser);
        return new MessageResponse("Transaction deleted successfully", id);
    }

    public TransactionEntity getTransactionForCurrentUser(Long id) {
        UserEntity currentUser = authService.getCurrentUser();
        return transactionRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() ->
                        new TransactionException("Transaction not found with id: " + id));
    }

}
