package ru.masta.orders.ordermodule.service;

import org.springframework.stereotype.Service;
import ru.masta.entity.entitymodule.entity.Item;
import ru.masta.entity.entitymodule.entity.Order;
import ru.masta.entity.entitymodule.entity.Purchase;

import java.util.Date;


/*
* Синтетический класс по добавлению тестовой покупки
* для нового пользователя.
* Создан только для тестирования Rabbit MQ
*
* */
@Service
public class TestUserData {

    private ItemService itemService;
    private PurchaseService purchaseService;
    private OrderService orderService;

    private final int TEST_ITEM_PRICE=100;
    private final String TEST_ITEM_NAME = "Test item ";

    public TestUserData(ItemService itemService, PurchaseService purchaseService, OrderService orderService) {
        this.itemService = itemService;
        this.purchaseService = purchaseService;
        this.orderService = orderService;
    }

    public void testDataInit(Long userId){
        Item item = new Item();
        item.setName(TEST_ITEM_NAME + userId);
        item.setPrice(TEST_ITEM_PRICE);

        item = itemService.addItem(item);

        Order order = new Order();
        order.setDate(new Date());
        order.setUserId(userId);
        order = orderService.add(order);

        Purchase purchase = new Purchase();
        purchase.setCount(1L);
        purchase.setOrder(order);
        purchase.setItem(item);
        purchaseService.add(purchase);

    }
}
