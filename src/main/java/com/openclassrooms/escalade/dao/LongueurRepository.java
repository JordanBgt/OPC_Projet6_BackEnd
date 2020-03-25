package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Longueur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LongueurRepository extends CrudRepository<Longueur, Long> {

    List<Longueur> findAll();
}
