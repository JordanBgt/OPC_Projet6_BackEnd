package com.openclassrooms.escalade.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commentaire")
public class Commentaire {

    @Id
    private Long id;

    private String description;

    private Date date;

    @ManyToOne
    private Utilisateur utilisateur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
