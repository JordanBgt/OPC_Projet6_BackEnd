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
@Table(name = "secteur")
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    @ManyToOne
    private Spot spot;

    @OneToMany(mappedBy = "secteur")
    private List<Voie> voies;
}
