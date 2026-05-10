package com.ainura.finance_tracker.item.service.impl;

import com.ainura.finance_tracker.item.mapper.ItemMapper;
import com.ainura.finance_tracker.item.model.dto.ItemResponse;
import com.ainura.finance_tracker.item.repository.ItemRepository;
import com.ainura.finance_tracker.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    @Override
    public Page<ItemResponse> getItems(Pageable pageable) {
        return itemRepository.findAll(pageable).map(mapper::toResponse);
    }
}
