package com.prueba.rickandmorty.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.rickandmorty.dataprovider.client.RickAndMortyFeingClient;
import com.prueba.rickandmorty.openapi.model.Character;
import com.prueba.rickandmorty.openapi.model.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharactersServiceImpl implements CharactersService{

    private final RickAndMortyFeingClient rickAndMortyFeingClient;

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
}