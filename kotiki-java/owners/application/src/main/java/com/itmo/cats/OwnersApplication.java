package com.itmo.cats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(OwnersRabbitMQConfiguration.class)
public class OwnersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnersApplication.class, args);
    }

}

