package com.prueba.rickandmorty.controllers;

import com.prueba.rickandmorty.openapi.CharactersApi;
import com.prueba.rickandmorty.openapi.model.Character;
import com.prueba.rickandmorty.service.CharactersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CharactersController implements CharactersApi {

    private final CharactersService charactersService;
    @Override
    public ResponseEntity<List<Character>> characters(){
        log.info("Starting get characters");
        List<Character> characterList = charactersService.getCharacters();
        return ResponseEntity.ok(characterList);
    }
}