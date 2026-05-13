package com.ainura.finance_tracker.transaction.repository;

import com.ainura.finance_tracker.transaction.enums.TransactionType;
import com.ainura.finance_tracker.transaction.model.dto.expense.ExpenseByCategory;
import com.ainura.finance_tracker.transaction.model.entity.TransactionEntity;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(
            value = """
                    SELECT SUM(t.amount)
                    FROM TransactionEntity t
                    WHERE t.transactionType = 'EXPENSE' AND t.user= :currentUser
                    """
    )
    BigDecimal getTotalExpense(@Param("currentUser") UserEntity currentUser);

    @Query(
            value = """
                    SELECT SUM(t.amount)
                    FROM TransactionEntity t
                    WHERE t.transactionType = 'INCOME' AND t.user= :currentUser
                    """

    )
    BigDecimal getTotalIncome(@Param("currentUser") UserEntity currentUser);

    @Query(
            value = """
                    SELECT new com.ainura.finance_tracker.transaction.model.dto.expense.ExpenseByCategory(t.category, SUM(t.amount))
                    FROM TransactionEntity t
                    WHERE t.transactionType= :type AND t.user= :currentUser
                    GROUP BY t.category
                    """
    )
    List<ExpenseByCategory> getExpenseByCategory(@Param("type") TransactionType type,  @Param("currentUser") UserEntity currentUser);

    Page<TransactionEntity> findAllByUser(UserEntity user, Pageable pageable);

    @Query(
            value = """
                    SELECT t FROM TransactionEntity t
                    WHERE t.id = :id AND t.user= :currentUser
                    """
    )
    Optional<TransactionEntity> findByIdAndUser(@Param("id") Long id, @Param("currentUser") UserEntity currentUser);


}
