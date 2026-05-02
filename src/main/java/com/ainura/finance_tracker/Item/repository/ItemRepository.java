package com.ainura.finance_tracker.Item.repository;

import com.ainura.finance_tracker.Item.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
