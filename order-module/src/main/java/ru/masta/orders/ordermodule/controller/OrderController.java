package ru.masta.orders.ordermodule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.masta.entitymodule.entity.Order;
import ru.masta.entitymodule.entity.Purchase;
import ru.masta.entitymodule.entity.UserData;
import ru.masta.orders.ordermodule.feign.UserFeignClient;
import ru.masta.orders.ordermodule.service.ItemService;
import ru.masta.orders.ordermodule.service.OrderService;
import ru.masta.orders.ordermodule.service.PurchaseService;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;
    private ItemService itemService;
    private PurchaseService purchaseService;
    private UserFeignClient userFeignClient;


    public OrderController(OrderService service, ItemService itemService, PurchaseService purchaseService, UserFeignClient userFeignClient) {
        this.service = service;
        this.itemService = itemService;
        this.purchaseService = purchaseService;
        this.userFeignClient = userFeignClient;
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
/*

        // Вызов без вероятности, что модуль "user-module" будет недоступен
        if(userFeignClient.findById(order.getUserId())==null){
            return new ResponseEntity("User with id: " + order.getUserId() + " not found", HttpStatus.NOT_ACCEPTABLE);
        }*/

        ResponseEntity<UserData> result = userFeignClient.findById(order.getUserId());


        // Need fix. If user not found call UserFeignClientFallback.findById ???

        if(result.getBody().getId()==-1){
            return new ResponseEntity("Subsystem USERS not available, please try again later", HttpStatus.NOT_ACCEPTABLE);
        }


        if(result.getBody().getId()==null){
            return new ResponseEntity("User with id: " + order.getUserId() + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(service.add(order));
    }

    private boolean checkItem(Long id){
        return itemService.findById(id).isPresent();
    }
}
