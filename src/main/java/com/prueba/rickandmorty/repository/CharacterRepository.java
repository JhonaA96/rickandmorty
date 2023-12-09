package com.prueba.rickandmorty.repository;

import com.prueba.rickandmorty.model.CharacterDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterDB, Long> {

    @Query(value = "SELECT EXISTS(SELECT c.name FROM character c WHERE c.name = ?1) ", nativeQuery = true)
    Boolean findByName(String name);
}