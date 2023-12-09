package com.prueba.rickandmorty.service.domain;


import com.prueba.rickandmorty.model.CharacterDB;
import com.prueba.rickandmorty.openapi.model.Character;

import java.util.List;

public interface CharactersService {
    List<Character> getCharacters();

    CharacterDB saveCharacter(Character character);
}