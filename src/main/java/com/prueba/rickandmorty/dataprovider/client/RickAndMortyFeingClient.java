package com.prueba.rickandmorty.dataprovider.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(
        name = "RickAndMortyFeingClient",
        url = "${provider.config.RickAndMorty.url}"
)
public interface RickAndMortyFeingClient {

    @GetMapping(
            value = "${provider.config.RickAndMorty.characters}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> getCharacters();
}