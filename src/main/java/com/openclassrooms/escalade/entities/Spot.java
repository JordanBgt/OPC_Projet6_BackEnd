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

    private String description;

    private boolean estOfficiel;

    @ManyToMany(mappedBy = "spots")
    private List<Topo> topos;

    @OneToMany
    private List<Commentaire> commentaires;

    @OneToMany
    private List<Photo> photos;

    @OneToMany(mappedBy = "spot")
    private List<Secteur> secteurs;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEstOfficiel() {
        return estOfficiel;
    }

    public void setEstOfficiel(boolean estOfficiel) {
        this.estOfficiel = estOfficiel;
    }
}
