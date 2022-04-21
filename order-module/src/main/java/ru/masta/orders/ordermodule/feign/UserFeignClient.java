package ru.masta.orders.ordermodule.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.masta.entitymodule.entity.UserData;

@FeignClient(name = "user-module", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @PostMapping("/users/id")
    ResponseEntity<UserData> findById(@RequestBody Long id);

}

@Component
class UserFeignClientFallback implements UserFeignClient{

    // Need fix. If user not found call UserFeignClientFallback.findById ???

    // если сервис "user-module" не будет доступен по адресу "/users/id"
    @Override
    public ResponseEntity<UserData> findById(Long id) {
        return ResponseEntity.ok(new UserData(-1L, null, null, null));
    }
}
