package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Voie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to handle Voie DAO operations
 */
@Repository
public interface VoieRepository extends CrudRepository<Voie, Long>, QuerydslPredicateExecutor<Voie> {
    @Query(value = "SELECT id FROM voie WHERE secteur_id = ?1", nativeQuery = true)
    List<Long> findAllBySecteurId(Long secteurId);
}
