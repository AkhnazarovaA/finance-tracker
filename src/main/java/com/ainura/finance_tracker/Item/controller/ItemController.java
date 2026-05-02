package com.ainura.finance_tracker.Item.controller;

import com.ainura.finance_tracker.Item.model.dto.ItemResponse;
import com.ainura.finance_tracker.Item.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemResponse> getItems() {
        return itemService.getItems();
    }

}
