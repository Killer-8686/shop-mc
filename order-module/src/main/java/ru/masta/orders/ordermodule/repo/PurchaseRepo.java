package ru.masta.orders.ordermodule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.masta.orders.ordermodule.entity.Purchase;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

    Purchase findByItemId(Long id);
}
