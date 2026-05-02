package com.ainura.finance_tracker.Item.service;

import com.ainura.finance_tracker.Item.model.dto.ItemResponse;

import java.util.List;

public interface ItemService {
    List<ItemResponse> getItems();
}
