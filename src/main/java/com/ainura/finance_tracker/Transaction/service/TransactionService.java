package com.ainura.finance_tracker.Transaction.service;

import com.ainura.finance_tracker.Transaction.model.dto.expense.ExpenseByCategory;
import com.ainura.finance_tracker.Transaction.model.dto.expense.TotalExpenseResponse;
import com.ainura.finance_tracker.Transaction.model.dto.income.TotalIncomeResponse;
import com.ainura.finance_tracker.Transaction.model.dto.request.TransactionRequest;
import com.ainura.finance_tracker.Transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.common.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    TransactionResponse createTransaction(TransactionRequest request);
    Page<TransactionResponse> getAll(Pageable pageable);
    TransactionResponse getTransactionById(Long id);
    MessageResponse deleteTransaction(Long id);
    TransactionResponse updateTransaction(Long id, TransactionRequest request);
    void patchTransaction(Long id, TransactionRequest request);
    TotalExpenseResponse getTotalExpense();
    TotalIncomeResponse getTotalIncome();
    List<ExpenseByCategory> getExpenseByCategory();

}
