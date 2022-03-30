package ru.masta.orders.ordermodule.service;

import org.springframework.stereotype.Service;
import ru.masta.orders.ordermodule.repo.ItemRepo;
import ru.masta.orders.ordermodule.entity.Item;

import java.util.Optional;

@Service
public class ItemService {

    private ItemRepo repo;

    public ItemService(ItemRepo repo) {
        this.repo = repo;
    }

    public Optional<Item> findById(Long id){
        return repo.findById(id);
    }
}
