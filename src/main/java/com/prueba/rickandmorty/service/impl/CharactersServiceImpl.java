package com.prueba.rickandmorty.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.rickandmorty.dataprovider.client.RickAndMortyFeingClient;
import com.prueba.rickandmorty.model.CharacterDB;
import com.prueba.rickandmorty.openapi.model.Character;
import com.prueba.rickandmorty.openapi.model.Result;
import com.prueba.rickandmorty.repository.CharacterRepository;
import com.prueba.rickandmorty.service.domain.CharactersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharactersServiceImpl implements CharactersService {

    private final RickAndMortyFeingClient rickAndMortyFeingClient;
    private final CharacterRepository characterRepository;
    private final CharacterDB characterDB = new CharacterDB();

    @Override
    public List<Character> getCharacters() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Character> characterList = new ArrayList<Character>();
        Character character = new Character();

        try {
            LinkedHashMap<String, ?> response = (LinkedHashMap<String, ?>) rickAndMortyFeingClient.getCharacters().getBody();
            JSONObject jsonObject = new JSONObject();

            for(String key: response.keySet()){
                jsonObject.put(key, response.get(key));
            }

            List<Result> resultsObj = objectMapper.convertValue(jsonObject.get("results"), new TypeReference<List<Result>>() {});
            for (Result result: resultsObj){
                character.setName(result.getName());
                character.setStatus(result.getStatus());
                character.setGender(result.getGender());
                character.setImage(result.getImage());

                characterList.add(character);
                character = new Character();
            }
        } catch (Exception e) {
            log.error("Error getting characters", e);
        }
        return characterList;
    }


    @Override
    public CharacterDB saveCharacter(Character character) {

        List<String> nameList = getCharacters().stream().map(Character::getName).collect(Collectors.toList());
        for (String name: nameList){
            if (name.equals(character.getName())){
                log.error("Character already exists in Rick and Morty API");
                throw new RuntimeException("Character already exists in Rick and Morty API");
            }
        }

        Boolean exists = characterRepository.findByName(character.getName());
        if (exists){
            log.error("Character already exists in DB");
            throw new RuntimeException("Character already exists in DB");
        }

        characterDB.setName(character.getName());
        characterDB.setStatus(character.getStatus());
        characterDB.setGender(character.getGender());
        characterDB.setImage(character.getImage());

        characterRepository.save(characterDB);

        log.info("Character saved successfully");

        return this.characterDB;
    }
}