package ru.masta.orders.ordermodule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.masta.orders.ordermodule.entity.Item;
import ru.masta.orders.ordermodule.repo.PurchaseRepo;
import ru.masta.orders.ordermodule.service.ItemService;
import ru.masta.orders.ordermodule.service.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Item> getAll (){
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Item> add(@RequestBody Item item){
        Item item1 = service.addItem(item);
        if(item1.getId()==-1){
            return new ResponseEntity("Id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(item1);
    }

    @PutMapping("/update")
    public ResponseEntity<Item> update(@RequestBody Item item){
        return service.update(item);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteById(@RequestBody Long id){
        return service.deleteById(id);
    }
}
