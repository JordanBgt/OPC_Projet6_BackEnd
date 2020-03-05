package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.entities.Longueur;
import com.openclassrooms.escalade.repositories.LongueurRepository;
import com.openclassrooms.escalade.services.LongueurService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LongueurServiceImpl implements LongueurService {

    private final LongueurRepository longueurRepository;

    public LongueurServiceImpl(LongueurRepository longueurRepository) {
        this.longueurRepository = longueurRepository;
    }

    public List<Longueur> findAll() {
        return longueurRepository.findAll();
    }

    public Longueur findById(Long id) {
        return longueurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La longueur demandée n'a pas été trouvée"));
    }

    public Longueur create(Longueur longueur) {
        return longueurRepository.save(longueur);
    }

    public Longueur update(Longueur longueur) {
        return longueurRepository.save(longueur);
    }

    public void delete(Long id) {
        Longueur longueur = this.findById(id);
        longueurRepository.delete(longueur);
    }
}
