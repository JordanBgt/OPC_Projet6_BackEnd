package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.repositories.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SpotService {

    private final SpotRepository spotRepository;

    @Autowired
    public SpotService(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    public List<Spot> findAll () {
        return spotRepository.findAll();
    }

    public Spot findById(Long id) {
        return spotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Le spot n'a pas été trouvé"));
    }

    public Spot create(Spot spot) {
        return spotRepository.save(spot);
    }

    public Spot update(Spot spot) {
        return spotRepository.save(spot);
    }

    public void delete(Long id) {
        Spot spot = this.findById(id);
        spotRepository.delete(spot);
    }
}
