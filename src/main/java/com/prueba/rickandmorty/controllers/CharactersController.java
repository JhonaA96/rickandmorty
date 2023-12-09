package com.prueba.rickandmorty.controllers;

import com.prueba.rickandmorty.model.CharacterDB;
import com.prueba.rickandmorty.openapi.CharactersApi;
import com.prueba.rickandmorty.openapi.model.Character;
import com.prueba.rickandmorty.openapi.model.Notification;
import com.prueba.rickandmorty.openapi.model.Response;
import com.prueba.rickandmorty.service.domain.CharactersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CharactersController implements CharactersApi{

    private final CharactersService charactersService;

    @Override
    public ResponseEntity<List<Character>> characters(){
        log.info("Starting get characters");
        List<Character> characterList = charactersService.getCharacters();
        return ResponseEntity.ok(characterList);
    }

    @Override
    public ResponseEntity<Response> saveCharacter(@Valid @RequestBody(required = false) Character character){
        log.info("Saving character");
        try{
            charactersService.saveCharacter(character);
            return ResponseEntity.ok(new Response().data(character));
        }catch (Exception e) {
            log.error("Character cant be saved", e);
            Notification notification = new Notification();
            notification.message("Character cant be saved");
            notification.code("500");
            notification.category("ERROR");
            return ResponseEntity.badRequest().body(new Response().notification(notification));
        }
    }
}