package com.openclassrooms.escalade.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String pays;
    private String ville;
    private Photo photo;

    @ManyToMany(mappedBy = "spots")
    private List<Topo> topos;

    @OneToMany
    private List<Commentaire> commentaires;

    @OneToMany
    private List<Photo> photos;

    @OneToMany(mappedBy = "spot")
    private List<Secteur> secteurs;


}
