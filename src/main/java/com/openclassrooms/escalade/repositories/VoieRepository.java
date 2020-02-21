package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Voie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoieRepository extends CrudRepository<Voie, Long> {

    List<Voie> findAll();
}
