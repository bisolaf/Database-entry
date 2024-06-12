package com.example.demo.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ClientConfig {
    @Bean

    CommandLineRunner commandLineRunner(ClientRepository repository){
        return args -> {
            Client Meta = new Client(
                            3L,
                            "Meta Constructions",
                            "metabuildings@constructions.com",
                            LocalDate.of(2004, Month.DECEMBER, 7)
                    );
            Client Darby = new Client(
                    4L,
                    "Darby Law Partners",
                    "darbyattorneys@law.org",
                    LocalDate.of(2007, Month.JULY, 7)
            );
            repository.saveAll(
                    List.of(Meta, Darby)
            );
        };
    }
    }

