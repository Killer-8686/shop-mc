package ru.masta.client.eurekaclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class ExampleController {

    @GetMapping("/test")
    public String get(){
        return "test string";
    }
}
