package ru.masta.user.usermodule.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.masta.user.usermodule.entity.UserData;
import ru.masta.user.usermodule.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public List<UserData> findAll() {
        return repo.findAll();
    }

    public Optional<UserData> getById(Long id) {
        return repo.findById(id);
    }

    public UserData addUser(UserData user){
        return repo.save(user);
    }

    public Page<UserData> findByParam(String name, String lastName, PageRequest pageable) {
        return repo.findByParam(name, lastName, pageable);
    }

    public UserData findByCardNumber(String card) {
        return repo.findByCard(card);
    }
}
