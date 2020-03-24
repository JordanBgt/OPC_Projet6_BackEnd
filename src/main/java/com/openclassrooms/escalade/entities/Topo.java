package com.openclassrooms.escalade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topo")
public class Topo implements Serializable {

    private static final long serialVersionUID = -6148715300769658435L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
