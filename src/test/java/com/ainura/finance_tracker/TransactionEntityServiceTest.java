package com.ainura.finance_tracker;

import com.ainura.finance_tracker.transaction.enums.TransactionType;
import com.ainura.finance_tracker.transaction.mapper.TransactionMapper;
import com.ainura.finance_tracker.transaction.model.dto.request.TransactionRequest;
import com.ainura.finance_tracker.transaction.model.dto.response.TransactionResponse;
import com.ainura.finance_tracker.transaction.model.entity.TransactionEntity;
import com.ainura.finance_tracker.transaction.repository.TransactionRepository;
import com.ainura.finance_tracker.transaction.service.impl.TransactionServiceImpl;
import com.ainura.finance_tracker.common.MessageResponse;
import com.ainura.finance_tracker.exception.TransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TransactionEntityServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl(transactionRepository, transactionMapper);
    }

    @Test
    @DisplayName("Should successfully save transaction with valid values")
    void createTransaction_Success() {
        // Arrange (preparing)
        TransactionRequest request = new TransactionRequest();
        request.setTransactionType(TransactionType.INCOME);
        request.setAmount(new BigDecimal(10000.00));
        request.setCategory("Bonus");
        request.setDescription("Monthly bonus from Bank");
        request.setTransactionDate(LocalDate.now());

        // To mock: when method 'save' is called, return this object
        when(transactionRepository.save(any(TransactionEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        // Act (action)
        TransactionResponse response = transactionService.createTransaction(request);

        // Assert (check that mapping is success)
        assertThat(response).isInstanceOf(TransactionResponse.class);
        assertThat(response).isNotNull();
        assertThat(response.transactionType()).isEqualTo(TransactionType.INCOME);
        assertThat(response.amount()).isEqualByComparingTo(new BigDecimal(10000.00));
        assertThat(response.category()).isEqualTo("Bonus");
        assertThat(response.description()).isEqualTo("Monthly bonus from Bank");
        assertThat(response.transactionDate()).isEqualTo(LocalDate.now());

        // checking that the method of repository was called 1 time
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    @DisplayName("Should return MessageResponse with ID after successful deletion")
    void delete_ShouldReturnMessageDto() {
        // arrange
        Long id = 16L;
        when(transactionRepository.existsById(id)).thenReturn(true);
        // act
        MessageResponse result = transactionService.deleteTransaction(id);
        // assert
        assertThat(result).isNotNull();
        assertThat(result.message()).isEqualTo("Transaction deleted successfully");
        assertThat(result.id()).isEqualTo(id);
        // verify
        verify(transactionRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should throw TransactionNotFoundException when trying to delete non-existent ID")
    void delete_ShouldThrownException_WhenIdDoesNotExist() {
        // arrange
        Long id = 16L;
        when(transactionRepository.existsById(id)).thenReturn(false);
        // assert
        assertThatThrownBy(() -> transactionService.deleteTransaction(id))
                .isInstanceOf(TransactionException.class);
        // verify
        verify(transactionRepository, never()).deleteById(any());
    }
}
