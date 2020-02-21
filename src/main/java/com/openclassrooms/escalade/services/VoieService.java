package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.repositories.VoieRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VoieService {

    private final VoieRepository voieRepository;

    public VoieService(VoieRepository voieRepository) {
        this.voieRepository = voieRepository;
    }

    public List<Voie> findAll() {
        return voieRepository.findAll();
    }

    public Voie findOne(Long id) {
        return voieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La voie demandée n'a pas été trouvée"));
    }

    public Voie create(Voie voie) {
        return voieRepository.save(voie);
    }

    public Voie update(Voie voie) {
        return voieRepository.save(voie);
    }

    public void delete(Long id) {
        Voie voie = this.findOne(id);
        voieRepository.delete(voie);
    }
}
