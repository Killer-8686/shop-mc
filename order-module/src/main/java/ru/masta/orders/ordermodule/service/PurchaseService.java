package ru.masta.orders.ordermodule.service;

import org.springframework.stereotype.Service;
import ru.masta.entitymodule.entity.Purchase;
import ru.masta.orders.ordermodule.repo.PurchaseRepo;

import java.util.List;

@Service
public class PurchaseService {

    private PurchaseRepo purchaseRepo;

    public PurchaseService(PurchaseRepo purchaseRepo) {
        this.purchaseRepo = purchaseRepo;
    }

    public void addAll(List<Purchase> purchases) {
        purchaseRepo.saveAll(purchases);
    }
}
