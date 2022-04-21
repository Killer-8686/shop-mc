package ru.masta.orders.ordermodule.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.masta.entity.entitymodule.entity.Item;
import ru.masta.orders.ordermodule.repo.ItemRepo;
import ru.masta.orders.ordermodule.repo.PurchaseRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private ItemRepo repo;
    private PurchaseRepo purchaseRepo;

    public ItemService(ItemRepo repo, PurchaseRepo purchaserepo) {
        this.repo = repo;
        this.purchaseRepo = purchaserepo;
    }

    public Optional<Item> findById(Long id){
        return repo.findById(id);
    }

    public List<Item> findAll() {
        return repo.findAll();
    }

    public Item addItem(Item item) {
        if(item.getId()!=0){
            item.setId(-1);
            return item;
        }
        return repo.save(item);
    }

    @Transactional
    @Modifying
    public ResponseEntity<Item> update(Item item) {
        if(item.getId()==0){
            return new ResponseEntity("id not must be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        Item item1;
        Optional<Item> ot = repo.findById(item.getId());

        if(ot.isEmpty()){
            return new ResponseEntity("Id not found", HttpStatus.NOT_ACCEPTABLE);
        }
        item1=ot.get();
        item1.setName(item.getName());
        item1.setPrice(item.getPrice());

        return ResponseEntity.ok(item);
    }

    public ResponseEntity deleteById(Long id) {


        if(purchaseRepo.findByItemId(id)!=null){
            return new ResponseEntity("Item is present in orders history. Item not must be deleted", HttpStatus.NOT_ACCEPTABLE);
        }
        repo.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
