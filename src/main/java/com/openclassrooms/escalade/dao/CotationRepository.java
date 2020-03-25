package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Cotation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotationRepository extends CrudRepository<Cotation, Long> {
    List<Cotation> findAll();
}
