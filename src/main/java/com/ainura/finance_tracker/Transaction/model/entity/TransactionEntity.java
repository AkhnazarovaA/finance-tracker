package com.ainura.finance_tracker.Transaction.model.entity;

import com.ainura.finance_tracker.Item.model.entity.ItemEntity;
import com.ainura.finance_tracker.Transaction.enums.TransactionType;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq")
    @SequenceGenerator(name = "trans_seq", sequenceName = "transaction_sequence", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String category;

    private String description;

    @CreatedDate
    @Column(name = "transaction_date", nullable = false, updatable = false)
    private LocalDate transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items ;

}
