package ru.masta.orders.ordermodule.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.masta.orders.ordermodule.service.ItemService;

@RestController
public class ItemController {

    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }
}
