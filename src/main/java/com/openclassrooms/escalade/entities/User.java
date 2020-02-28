package com.openclassrooms.escalade.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private String email;

    @ManyToMany
    private List<Role> roles;

    @OneToMany(mappedBy = "topoCreator")
    private List<Topo> toposCreated;

    @OneToMany(mappedBy = "topoTenant")
    private List<Topo> toposRent;

    @OneToMany(mappedBy = "user")
    private List<Spot> spotsCreated;


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

    public List<Topo> getToposCreated() {
        return toposCreated;
    }

    public void setToposCreated(List<Topo> toposCreated) {
        this.toposCreated = toposCreated;
    }

    public List<Topo> getToposRent() {
        return toposRent;
    }

    public void setToposRent(List<Topo> toposRent) {
        this.toposRent = toposRent;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Spot> getSpotsCreated() {
        return spotsCreated;
    }

    public void setSpotsCreated(List<Spot> spotsCreated) {
        this.spotsCreated = spotsCreated;
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
