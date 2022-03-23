package ru.masta.client.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class ExampleController {

    @Value("${eureka.instance.instance-id}")
    private String id;

    @GetMapping("/test")
    public String get(){
        return "test string from " + id;
    }
}
