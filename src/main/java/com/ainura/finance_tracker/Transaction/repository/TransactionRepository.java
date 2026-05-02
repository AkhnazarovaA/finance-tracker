package com.ainura.finance_tracker.Transaction.repository;

import com.ainura.finance_tracker.Transaction.model.dto.expense.ExpenseByCategory;
import com.ainura.finance_tracker.Transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.Transaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    TransactionResponse save(TransactionResponse response);
    List<Transaction> findAllByOrderByIdAsc();

    @Query(
            value = "SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE transaction_type = 'EXPENSE'",
            nativeQuery = true
    )
    BigDecimal getTotalExpense();

    @Query(
            value = "SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE transaction_type = 'INCOME'",
            nativeQuery = true

    )
    BigDecimal getTotalIncome();

    @Query(
            value = "SELECT category, COALESCE(SUM(amount),0) AS totalAmount " +
                    "FROM transactions " +
                    "WHERE transaction_type = 'EXPENSE' " +
                    "GROUP BY category",
            nativeQuery = true
    )
    List<ExpenseByCategory> getExpenseByCategory();


}
