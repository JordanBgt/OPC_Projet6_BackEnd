package com.openclassrooms.escalade.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "secteur")
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String nom;

    @ManyToOne
    private Spot spot;

    @OneToMany(mappedBy = "secteur")
    private List<Voie> voies;
}
