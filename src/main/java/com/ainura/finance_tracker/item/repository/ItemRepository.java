package com.ainura.finance_tracker.item.repository;

import com.ainura.finance_tracker.item.model.entity.ItemEntity;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Page<ItemEntity> findAllByTransactionUser(UserEntity currentUser, Pageable pageable);
}
