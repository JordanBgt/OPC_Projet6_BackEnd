package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Spot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpotRepository extends CrudRepository<Spot, Long> {

    List<Spot> findAll();
}
