package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.repositories.SecteurRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SecteurService {

    private final SecteurRepository secteurRepository;

    public SecteurService(SecteurRepository secteurRepository) {
        this.secteurRepository = secteurRepository;
    }

    public List<Secteur> findAll() {
        return secteurRepository.findAll();
    }

    public Secteur findById(Long id) {
        return secteurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Le secteur demandé n'a pas été trouvé"));
    }

    public Secteur create(Secteur secteur) {
        return secteurRepository.save(secteur);
    }

    public Secteur update(Secteur secteur) {
        return secteurRepository.save(secteur);
    }

    public void delete(Long id) {
        Secteur secteur = this.findById(id);
        secteurRepository.delete(secteur);
    }
}
