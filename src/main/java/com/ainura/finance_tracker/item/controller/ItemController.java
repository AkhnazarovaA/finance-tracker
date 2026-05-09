package com.ainura.finance_tracker.item.controller;

import com.ainura.finance_tracker.item.model.dto.ItemResponse;
import com.ainura.finance_tracker.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Items", description = "Item management endpoints")
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "Get all items")
    @GetMapping
    public Page<ItemResponse> getItems(Pageable pageable) {
        return itemService.getItems(pageable);
    }

}
