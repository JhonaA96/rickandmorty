package com.prueba.rickandmorty;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class RickAndMortyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RickAndMortyApplication.class, args);
    }

}