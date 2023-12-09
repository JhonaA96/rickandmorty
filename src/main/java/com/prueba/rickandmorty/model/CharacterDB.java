package com.prueba.rickandmorty.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "character")
public class CharacterDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "gender")
    private String gender;

    @Column(name = "image")
    private String image;
}