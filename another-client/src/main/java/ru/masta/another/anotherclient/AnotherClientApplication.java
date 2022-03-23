package ru.masta.another.anotherclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AnotherClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnotherClientApplication.class, args);
    }

}
