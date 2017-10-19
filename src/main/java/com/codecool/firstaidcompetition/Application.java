package com.codecool.firstaidcompetition;

import com.codecool.firstaidcompetition.repository.DBHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(DBHandler dbHandler) {
        return args -> {
            dbHandler.populateDB();
        };
    }
}
