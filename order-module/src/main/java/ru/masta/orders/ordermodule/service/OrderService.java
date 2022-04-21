package ru.masta.orders.ordermodule.service;

import org.springframework.stereotype.Service;
import ru.masta.entity.entitymodule.entity.Order;
import ru.masta.orders.ordermodule.repo.OrderRepo;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public Optional<Order> findById(Long id) {
        return repo.findById(id);
    }

    public List<Order> getAll() {
        return repo.findAll();
    }

    public Order add(Order order) {
        return repo.save(order);
    }
}
