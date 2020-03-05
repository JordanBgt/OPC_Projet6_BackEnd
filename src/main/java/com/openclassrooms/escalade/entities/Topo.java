package com.openclassrooms.escalade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topo")
public class Topo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;
    private String name;
    private String description;
    private String cotation;
    private String duration;

    @Column(name = "available")
    private boolean available;

    private String country;
    private String region;

    @OneToMany(mappedBy = "topo")
    private List<Spot> spots;

    @ManyToOne
    private User topoCreator;

    @ManyToOne
    private User topoTenant;

    @Column(name = "publication_date")
    private Date publicationDate;

    @OneToOne
    private Photo photo;
}
