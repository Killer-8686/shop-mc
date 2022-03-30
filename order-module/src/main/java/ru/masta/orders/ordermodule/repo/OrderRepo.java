package ru.masta.orders.ordermodule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.masta.orders.ordermodule.entity.Order;


@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
