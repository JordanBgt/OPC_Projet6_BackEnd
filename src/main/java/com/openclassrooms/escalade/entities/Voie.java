package com.openclassrooms.escalade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "voie")
public class Voie implements Serializable {

    private static final long serialVersionUID = -7599721237767396506L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "cotation_min_id")
    private Cotation cotationMin;

    @ManyToOne
    @JoinColumn(name = "cotation_max_id")
    private Cotation cotationMax;

    @ManyToOne
    @JoinColumn(name = "secteur_id")
    private Secteur secteur;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
