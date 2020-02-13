package com.openclassrooms.escalade.entities;

import com.openclassrooms.escalade.model.ERole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_utilisateur")
    private String username;

    @Column(name = "mot_de_passe")
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Topo> getToposCrees() {
        return toposCrees;
    }

    public void setToposCrees(List<Topo> toposCrees) {
        this.toposCrees = toposCrees;
    }

    public List<Topo> getToposReserves() {
        return toposReserves;
    }

    public void setToposReserves(List<Topo> toposReserves) {
        this.toposReserves = toposReserves;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
