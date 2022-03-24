package ru.masta.client.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Cont {

    @Value("${tmpVar}")
    private Integer tmpVar;

    @Value("${eureka.instance.instance-id}")
    private String id;

    @GetMapping("/test")
    public String tm(){
        return "tmpVar = " + tmpVar + ", instance = " + id;
    }
}
