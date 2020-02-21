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

    @ManyToOne
    private Topo topo;

    @OneToMany(mappedBy = "spot")
    private List<Commentaire> commentaires;

    @OneToMany
    private List<Photo> photos;

    @OneToMany(mappedBy = "spot")
    private List<Secteur> secteurs;

    @ManyToOne
    private Utilisateur utilisateur;

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

    public Topo getTopos() {
        return topo;
    }

    public void setTopos(Topo topo) {
        this.topo = topo;
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

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
