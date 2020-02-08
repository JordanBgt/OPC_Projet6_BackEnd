package com.openclassrooms.escalade.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "topo")
public class Topo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;
    private String nom;
    private String description;
    private String cotation;
    private String duree;

    @Column(name = "est_disponible")
    private Boolean estDisponible;
    private String pays;
    private String region;

    @ManyToMany
    private List<Spot> spots;

    @Column(name = "createur_topo")
    @ManyToOne
    private Utilisateur createurTopo;

    @Column(name = "reservant_topo")
    @ManyToOne
    private Utilisateur reservantTopo;

    @Column(name = "date_parution")
    private Date dateParution;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Boolean getEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(Boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Utilisateur getCreateurTopo() {
        return createurTopo;
    }

    public void setCreateurTopo(Utilisateur createurTopo) {
        this.createurTopo = createurTopo;
    }

    public Utilisateur getReservantTopo() {
        return reservantTopo;
    }

    public void setReservantTopo(Utilisateur reservantTopo) {
        this.reservantTopo = reservantTopo;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }
}
