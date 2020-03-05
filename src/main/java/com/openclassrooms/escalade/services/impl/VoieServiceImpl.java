package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.repositories.VoieRepository;
import com.openclassrooms.escalade.services.VoieService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VoieServiceImpl implements VoieService {

    private final VoieRepository voieRepository;

    public VoieServiceImpl(VoieRepository voieRepository) {
        this.voieRepository = voieRepository;
    }

    public List<Voie> findAll() {
        return voieRepository.findAll();
    }

    public Voie findById(Long id) {
        return voieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La voie demandée n'a pas été trouvée"));
    }

    public Voie create(Voie voie) {
        return voieRepository.save(voie);
    }

    public Voie update(Voie voie) {
        return voieRepository.save(voie);
    }

    public void delete(Long id) {
        Voie voie = this.findById(id);
        voieRepository.delete(voie);
    }
}
