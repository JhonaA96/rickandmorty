package com.prueba.rickandmorty.service;


import com.prueba.rickandmorty.openapi.model.Character;

import java.util.List;

public interface CharactersService {
    List<Character> getCharacters();
}