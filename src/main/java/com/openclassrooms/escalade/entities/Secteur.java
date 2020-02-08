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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public List<Voie> getVoies() {
        return voies;
    }

    public void setVoies(List<Voie> voies) {
        this.voies = voies;
    }
}
