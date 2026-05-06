package com.ainura.finance_tracker.Item.controller;

import com.ainura.finance_tracker.Item.model.dto.ItemResponse;
import com.ainura.finance_tracker.Item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Items", description = "Item management endpoints")
@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "Get all items")
    @GetMapping
    public List<ItemResponse> getItems() {
        return itemService.getItems();
    }

}
