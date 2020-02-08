package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Spot;
import org.springframework.data.repository.CrudRepository;

public interface SpotRepository extends CrudRepository<Spot, Long> {
}
