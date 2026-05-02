package com.ainura.finance_tracker.Transaction.service.impl;

import com.ainura.finance_tracker.Transaction.mapper.TransactionMapper;
import com.ainura.finance_tracker.Transaction.model.dto.expense.ExpenseByCategory;
import com.ainura.finance_tracker.Transaction.model.dto.expense.TotalExpenseResponse;
import com.ainura.finance_tracker.Transaction.model.dto.income.TotalIncomeResponse;
import com.ainura.finance_tracker.Transaction.model.dto.request.TransactionRequest;
import com.ainura.finance_tracker.Transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.Transaction.model.entity.Transaction;
import com.ainura.finance_tracker.Transaction.repository.TransactionRepository;
import com.ainura.finance_tracker.Transaction.service.TransactionService;
import com.ainura.finance_tracker.common.MessageResponse;
import com.ainura.finance_tracker.exception.TransactionException;
import lombok.RequiredArgsConstructor;
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
        Transaction transaction = mapper.toEntity(request);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapper.toResponse(savedTransaction);
    }

    @Override
    public List<Transaction> findAllByOrderByIdAsc() {
        return transactionRepository.findAllByOrderByIdAsc();
    }

    @Override
    public TransactionResponse findTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new TransactionException("Transaction not found with id: " + id));
        return mapper.toResponse(transaction);
    }

    @Override
    @Transactional
    public TransactionResponse updateTransaction(Long id, TransactionRequest request) {
        // Step 1: find an old entity from DB
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionException("Transaction not found with id:  + id"));

        // Step 2: MapStruct updates fields of 'transaction' FROM request
        mapper.updateEntityFromRequest(request, transaction);

        // Step 3: Save the updated entity
        Transaction updated = transactionRepository.save(transaction);
        return mapper.toResponse(updated);

    }

    @Override
    //TODO: RESPONSE TYPE SHOULD EXIST
    public void patchTransaction(Long id, TransactionRequest request) {
        Transaction transaction = findById(id, request);

        if (request.getTransactionType() != null) {
            transaction.setTransactionType(request.getTransactionType());
        }
        if (request.getAmount() != null) {
            transaction.setAmount(request.getAmount());
        }
        if (request.getCategory() != null) {
            transaction.setCategory(request.getCategory());
        }
        if (request.getDescription() != null) {
            transaction.setDescription(request.getDescription());
        }
        if (request.getTransactionDate() != null) {
            transaction.setTransactionDate(request.getTransactionDate());
        }
        transactionRepository.save(transaction);

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

    private Transaction findById(Long id, TransactionRequest request) {
        Transaction transaction;
        return transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new TransactionException("Transaction not found with id: " + id));
    }

}
