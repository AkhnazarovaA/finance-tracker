package com.ainura.finance_tracker.Transaction.controller;

import com.ainura.finance_tracker.Transaction.model.dto.expense.ExpenseByCategory;
import com.ainura.finance_tracker.Transaction.model.dto.expense.TotalExpenseResponse;
import com.ainura.finance_tracker.Transaction.model.dto.income.TotalIncomeResponse;
import com.ainura.finance_tracker.Transaction.model.dto.request.TransactionRequest;
import com.ainura.finance_tracker.Transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.Transaction.model.entity.Transaction;
import com.ainura.finance_tracker.Transaction.service.TransactionService;
import com.ainura.finance_tracker.common.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping()
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping
    public List<Transaction> findAllByOrderByIdAsc() {
        return transactionService.findAllByOrderByIdAsc();
    }

    @GetMapping("/{id}")
    public TransactionResponse findTransactionById(@PathVariable("id") Long id) {
        return transactionService.findTransactionById(id);
    }

    @PutMapping("/{id}")
    public TransactionResponse updateTransaction(@PathVariable Long id, @Valid @RequestBody TransactionRequest request) {
        return transactionService.updateTransaction(id, request);
    }

    @PatchMapping("/{id}")
    public void patchTransaction(@PathVariable Long id, @Valid @RequestBody TransactionRequest request) {
        transactionService.patchTransaction(id, request);
    }

    @GetMapping("/summary/expenses") //TODO: filtr by INCOME/EXPENSE
    public TotalExpenseResponse getTotalExpense() {
        return transactionService.getTotalExpense();
    }

    @GetMapping("/summary/incomes")
    public TotalIncomeResponse getTotalIncome() {
        return transactionService.getTotalIncome();
    }

    @GetMapping("/expenses/category")
    public List<ExpenseByCategory> getExpenseByCategory() {
        return transactionService.getExpenseByCategory();
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteTransaction(@PathVariable Long id) {
        return transactionService.deleteTransaction(id);
    }


    //TODO: filtering , summary endpoints (current balance)

    // Then later version 2:
    //categories as separate entity
    //monthly reports
    //auth

}
