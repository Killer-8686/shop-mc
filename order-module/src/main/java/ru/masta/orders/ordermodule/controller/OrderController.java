package ru.masta.orders.ordermodule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.masta.orders.ordermodule.entity.Item;
import ru.masta.orders.ordermodule.entity.Order;
import ru.masta.orders.ordermodule.entity.Purchase;
import ru.masta.orders.ordermodule.service.ItemService;
import ru.masta.orders.ordermodule.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;
    private ItemService itemService;


    public OrderController(OrderService service, ItemService itemService) {
        this.service = service;
        this.itemService = itemService;
    }

    @RequestMapping("/id")
    public ResponseEntity<Order> findById(@RequestBody Long id){
        Order order;
        try{
            order = service.findById(id).get();
        }catch (Exception e){
            return new ResponseEntity("ID not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(order);
    }

    @RequestMapping("/all")
    public List<Order> all(){

        return service.getAll();
    }

    @RequestMapping("/add")
    public ResponseEntity<Order> add(@RequestBody Order order){
        if(order.getId()!=null){
            return new ResponseEntity("ID must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if(order.getDate()==null){
            return new ResponseEntity("Date not must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if(order.getPurchases().isEmpty()){
            return new ResponseEntity("Purchases is empty", HttpStatus.NOT_ACCEPTABLE);
        }else{
            for(Purchase purchase: order.getPurchases()){
                if(purchase.getId()!=null
                        ||purchase.getCount()<1
                        ||!this.checkItem(purchase.getItem().getId())){
                    return new ResponseEntity("Purchases not correctly", HttpStatus.NOT_ACCEPTABLE);
                }
            }
        }
        return ResponseEntity.ok(service.add(order));
    }

    private boolean checkItem(Long id){
        return itemService.findById(id).isPresent();
    }
}
