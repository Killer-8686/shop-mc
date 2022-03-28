package ru.masta.user.usermodule.controller;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.masta.user.usermodule.entity.UserData;
import ru.masta.user.usermodule.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserDataController {

    private UserService service;

    public UserDataController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<UserData> findAll(){
        return service.findAll();
    }

    @PostMapping("/id")
    public ResponseEntity<UserData> getById(@RequestBody Long id){
        UserData user = null;
        try{
            user = service.getById(id).get();
        }catch (Exception w){
            return new ResponseEntity("id : " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<UserData> addUser(@RequestBody UserData user){

        if(user.getId()!=null){
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        UserData user1 = null;
        try {
            user1 = service.addUser(user);
        }catch (Exception w){
            return new ResponseEntity("Name, lastname or card is exists", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(user1);
    }

    @Transactional
    @PostMapping("/update")
    public ResponseEntity<UserData> update(@RequestBody UserData user){
        Optional<UserData> opt = service.getById(user.getId());
        if(opt.isEmpty()) {
            return new ResponseEntity("User with id = " + user.getId() + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        UserData user1 = opt.get();
        user1.setCard(user.getCard());
        user1.setLastname(user.getLastname());
        user1.setName(user.getName());
        return ResponseEntity.ok(user1);

    }

    /*
    *  DELETE запрещен, что бы сохранялись данные по покупкам, сделанным в прошлом. Целостность данных.
    * */
}
