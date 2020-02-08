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

    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }

    public Voie getVoie() {
        return voie;
    }

    public void setVoie(Voie voie) {
        this.voie = voie;
    }
}
