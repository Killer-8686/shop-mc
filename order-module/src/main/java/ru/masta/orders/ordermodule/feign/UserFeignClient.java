package ru.masta.orders.ordermodule.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.masta.entitymodule.entity.UserData;

@FeignClient(name = "user-module")
public interface UserFeignClient {

    @PostMapping("/users/id")
    ResponseEntity<UserData> findById(@RequestBody Long id);

}
