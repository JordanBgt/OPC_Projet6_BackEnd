package com.openclassrooms.escalade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String country;

    private String city;

    private String description;

    private boolean isOfficial = false;

    @ManyToOne
    private Topo topo;

    @OneToMany(mappedBy = "spot")
    private List<Comment> comments;

    @OneToMany
    private List<Photo> photos;

    @OneToMany(mappedBy = "spot")
    private List<Secteur> secteurs;

    @ManyToOne
    private User user;
}
