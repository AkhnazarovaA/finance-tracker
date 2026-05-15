package com.ainura.finance_tracker.item.service.impl;

import com.ainura.finance_tracker.auth.service.AuthService;
import com.ainura.finance_tracker.item.mapper.ItemMapper;
import com.ainura.finance_tracker.item.model.dto.ItemCreateRequest;
import com.ainura.finance_tracker.item.model.dto.ItemResponse;
import com.ainura.finance_tracker.item.model.entity.ItemEntity;
import com.ainura.finance_tracker.item.repository.ItemRepository;
import com.ainura.finance_tracker.item.service.ItemService;
import com.ainura.finance_tracker.transaction.model.entity.TransactionEntity;
import com.ainura.finance_tracker.transaction.service.TransactionService;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper mapper;
    private final AuthService authService;
    private final TransactionService transactionService;

    @Override
    @Transactional(readOnly = true)
    public Page<ItemResponse> getItems(Pageable pageable) {
        UserEntity currentUser = authService.getCurrentUser();
        return itemRepository.findAllByTransactionUser(currentUser, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public ItemResponse createItem(Long transactionId, ItemCreateRequest request) {
        TransactionEntity transactionEntity = transactionService.getTransactionForCurrentUser(transactionId);
        ItemEntity item = ItemEntity.builder()
                .name(request.name())
                .amount(request.amount())
                .quantity(request.quantity())
                .transaction(transactionEntity)
                .build();
        return mapper.toResponse(itemRepository.save(item));
    }
}
