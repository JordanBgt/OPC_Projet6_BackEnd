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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public User getTopoCreator() {
        return topoCreator;
    }

    public void setTopoCreator(User topoCreator) {
        this.topoCreator = topoCreator;
    }

    public User getTopoTenant() {
        return topoTenant;
    }

    public void setTopoTenant(User topoTenant) {
        this.topoTenant = topoTenant;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
