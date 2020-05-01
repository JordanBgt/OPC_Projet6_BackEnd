package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Secteur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecteurRepository extends CrudRepository<Secteur, Long>, QuerydslPredicateExecutor<Secteur> {
    @Query(value = "SELECT id FROM secteur WHERE spot_id = ?1", nativeQuery = true)
    List<Long> findAllBySpotId(Long spotId);
}
