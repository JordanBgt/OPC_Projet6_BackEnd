package com.openclassrooms.escalade.entities;

import javax.persistence.*;

@Entity
@Table(name = "longueur")
public class Longueur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String nom;
    private String cotation;

    @ManyToOne
    private Voie voie;

}
