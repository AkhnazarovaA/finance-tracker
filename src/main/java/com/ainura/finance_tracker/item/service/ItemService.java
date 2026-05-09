package com.ainura.finance_tracker.item.service;

import com.ainura.finance_tracker.item.model.dto.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemService {
    Page<ItemResponse> getItems(Pageable pageable);
}
