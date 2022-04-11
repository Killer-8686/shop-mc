package ru.masta.user.usermodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.masta.user.usermodule.entity.UserData;
import ru.masta.user.usermodule.search.UserSearchValues;
import ru.masta.user.usermodule.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserDataController {

    private final String ID_COLUMN = "id"; // имя столбца id, нужна для сортировки
    private final String NAME_SORT_COLUMN = "name";
    private int DEFAULT_PAGE_SIZE=100;
    private final UserService service;

    @Autowired
    public UserDataController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<UserData> findAll() {
        System.out.println(service);
        return service.findAll();
    }

    @PostMapping("/id")
    public ResponseEntity<UserData> getById(@RequestBody Long id) {
        UserData user = null;
        try {
            System.out.println(service);
            user = service.getById(id).get();
        } catch (Exception w) {
            return new ResponseEntity("id : " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<UserData> addUser(@RequestBody UserData user) {

        if (user.getId() != null) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        UserData user1 = null;
        try {
            user1 = service.addUser(user);
        } catch (Exception w) {
            return new ResponseEntity("Name, lastname or card is exists", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(user1);
    }

    @Transactional
    @PostMapping("/update")
    public ResponseEntity<UserData> update(@RequestBody UserData user) {
        Optional<UserData> opt = service.getById(user.getId());
        if (opt.isEmpty()) {
            return new ResponseEntity("User with id = " + user.getId() + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        UserData user1 = opt.get();
        user1.setCard(user.getCard());
        user1.setLastname(user.getLastname());
        user1.setName(user.getName());
        return ResponseEntity.ok(user1);

    }

    @PostMapping("/anyuser")
    public ResponseEntity<Page<UserData>> findByParam(@RequestBody UserSearchValues userSearchValues){

        String name = userSearchValues.getName()!=null ? userSearchValues.getName(): "";

        String lastName = userSearchValues.getLastName()!=null ? userSearchValues.getLastName(): "";

        Integer pageNumber = userSearchValues.getPageNumber() != null ? userSearchValues.getPageNumber() : 0;
        Integer pageSize = userSearchValues.getPageSize() != null ? userSearchValues.getPageSize() : DEFAULT_PAGE_SIZE;

        String sortColumn = userSearchValues.getSortColumn() != null ? userSearchValues.getSortColumn() : NAME_SORT_COLUMN;
        String sortDirection = userSearchValues.getSortDirection()!= null? userSearchValues.getSortDirection() : "";

        Sort.Direction direction = sortDirection.trim().length()==0
                ||sortDirection.trim().equals("asc")? Sort.Direction.ASC: Sort.Direction.DESC;


        Sort sort = Sort.by(direction, sortColumn, ID_COLUMN);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<UserData> page = service.findByParam(name, lastName, pageRequest);

        return ResponseEntity.ok(page);

    }

    @PostMapping("/card")
    public ResponseEntity<UserData> findByCard(@RequestBody String cardNumber){
        UserData user;
        System.out.println(service);

        try{
            user = service.findByCardNumber(cardNumber);
        }catch (Exception e){
            return new ResponseEntity("Card number not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(user);
    }


    /*
     *  DELETE запрещен, что бы сохранялись данные по покупкам, сделанным в прошлом. Целостность данных.
     * */
}
