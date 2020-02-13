package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Utilisateur;
import com.openclassrooms.escalade.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

public class UtilisateurService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Objects.requireNonNull(username);
        return utilisateurRepository.findUtilisateurByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
    }
}
