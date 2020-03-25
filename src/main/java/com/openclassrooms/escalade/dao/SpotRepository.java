package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Spot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpotRepository extends CrudRepository<Spot, Long> {

    List<Spot> findAll();
}
