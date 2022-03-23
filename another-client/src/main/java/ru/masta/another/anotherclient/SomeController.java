package ru.masta.another.anotherclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/another_main")
public class SomeController {

    @GetMapping("/test")
    public String get(){
        return "new test string from another controller";
    }
}
