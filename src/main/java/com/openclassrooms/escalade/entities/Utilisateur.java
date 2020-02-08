package com.openclassrooms.escalade.entities;

import com.openclassrooms.escalade.model.ERole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom_utilisateur")
    private String nomUtilisateur;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    private String email;

    private ERole role;

    @OneToMany(mappedBy = "createurTopo")
    private List<Topo> toposCrees;

    @OneToMany(mappedBy = "reservantTopo")
    private List<Topo> toposReserves;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
