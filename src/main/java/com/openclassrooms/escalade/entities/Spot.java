package com.openclassrooms.escalade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "spot")
public class Spot implements Serializable {

    private static final long serialVersionUID = -2259423736939841094L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private String city;

    private String name;

    private String cotation;

    @Lob
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
