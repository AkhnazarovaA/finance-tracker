package com.ainura.finance_tracker.transaction.controller;

import com.ainura.finance_tracker.transaction.model.dto.expense.ExpenseByCategory;
import com.ainura.finance_tracker.transaction.model.dto.expense.TotalExpenseResponse;
import com.ainura.finance_tracker.transaction.model.dto.income.TotalIncomeResponse;
import com.ainura.finance_tracker.transaction.model.dto.request.TransactionCreateRequest;
import com.ainura.finance_tracker.transaction.model.dto.request.TransactionPatchRequest;
import com.ainura.finance_tracker.transaction.model.dto.request.TransactionUpdateRequest;
import com.ainura.finance_tracker.transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.transaction.service.TransactionService;
import com.ainura.finance_tracker.common.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transactions", description = "Transaction management endpoints")
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Create a transaction")
    @PostMapping()
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionCreateRequest request) {
        return transactionService.createTransaction(request);
    }

    @Operation(summary = "Get all transactions")
    @GetMapping
    public Page<TransactionResponse> getAll(Pageable pageable) {
        return transactionService.getAll(pageable);
    }

    @Operation(summary = "Get transaction by id")
    @GetMapping("/{id}")
    public TransactionResponse getTransactionById(@PathVariable("id") Long id) {
        return transactionService.getTransactionById(id);
    }

    @Operation(summary = "Update a transaction")
    @PutMapping("/{id}")
    public TransactionResponse updateTransaction(@PathVariable Long id, @Valid @RequestBody TransactionUpdateRequest request) {
        return transactionService.updateTransaction(id, request);
    }

    @Operation(summary = "Partially update a transaction")
    @PatchMapping("/{id}")
    public MessageResponse patchTransaction(@PathVariable Long id, @RequestBody TransactionPatchRequest request) {
        return transactionService.patchTransaction(id, request);
    }

    @Operation(summary = "Get total expenses")
    @GetMapping("/summary/expenses") //TODO: filtr by INCOME/EXPENSE
    public TotalExpenseResponse getTotalExpense() {
        return transactionService.getTotalExpense();
    }

    @Operation(summary = "Get total incomes")
    @GetMapping("/summary/incomes")
    public TotalIncomeResponse getTotalIncome() {
        return transactionService.getTotalIncome();
    }

    @Operation(summary = "Get expenses by category")
    @GetMapping("/expenses/category")
    public List<ExpenseByCategory> getExpenseByCategory() {
        return transactionService.getExpenseByCategory();
    }

    @Operation(summary = "Delete a transaction")
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
