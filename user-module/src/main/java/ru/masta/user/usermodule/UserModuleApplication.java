package ru.masta.user.usermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"ru.masta"})
@EnableJpaRepositories(basePackages = {"ru.masta"})
public class UserModuleApplication {


    public static void main(String[] args) {


        SpringApplication.run(UserModuleApplication.class, args);


    }

}
