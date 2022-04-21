package ru.masta.orders.ordermodule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.masta.entity.entitymodule.entity.Order;
import ru.masta.entity.entitymodule.entity.Purchase;
import ru.masta.orders.ordermodule.service.ItemService;
import ru.masta.orders.ordermodule.service.OrderService;
import ru.masta.orders.ordermodule.service.PurchaseService;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;
    private ItemService itemService;
    private PurchaseService purchaseService;


    public OrderController(OrderService service, ItemService itemService, PurchaseService purchaseService) {
        this.service = service;
        this.itemService = itemService;
        this.purchaseService = purchaseService;
    }

    @RequestMapping("/id")
    public ResponseEntity findById(@RequestBody Long id){

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
    public ResponseEntity add(@RequestBody Order order){
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

        //purchaseService.addAll(order.getPurchases());
        return ResponseEntity.ok(service.add(order));
    }

    private boolean checkItem(Long id){
        return itemService.findById(id).isPresent();
    }
}
