package com.openclassrooms.escalade.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "voie")
public class Voie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String nom;
    private String cotation;

    @ManyToOne
    private Secteur secteur;

    @OneToMany(mappedBy = "voie")
    private List<Longueur> longueurs;
}
