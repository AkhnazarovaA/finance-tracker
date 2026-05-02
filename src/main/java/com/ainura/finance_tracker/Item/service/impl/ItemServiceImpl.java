package com.ainura.finance_tracker.Item.service.impl;

import com.ainura.finance_tracker.Item.model.dto.ItemResponse;
import com.ainura.finance_tracker.Item.model.entity.Item;
import com.ainura.finance_tracker.Item.repository.ItemRepository;
import com.ainura.finance_tracker.Item.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemResponse> getItems() {
        // in DB item is entity ---> itemResponse
        List<Item> items = itemRepository.findAll();

        List<ItemResponse> responses = new ArrayList<>();
        for (Item item : items) { //forEach goes through the items list one object at a time
                                   // items = input list
                                    // item = one current element
                                    //responses = output list
            ItemResponse response = new ItemResponse();
            response.setId(item.getId());
            response.setName(item.getName());
            response.setAmount(item.getAmount());
            response.setQuantity(item.getQuantity());

            if (item.getTransaction() != null) {
                response.setTransactionId(item.getTransaction().getId());
            }
            responses.add(response);

        }
        return responses;

    }
}
