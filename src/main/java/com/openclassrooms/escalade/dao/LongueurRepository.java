package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entity.Longueur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository to handle Longueur DAO operations
 */
@Repository
public interface LongueurRepository extends CrudRepository<Longueur, Long>, QuerydslPredicateExecutor<Longueur> {
    @Query(value = "SELECT id FROM longueur WHERE voie_id = ?1", nativeQuery = true)
    List<Long> findAllByVoieId(Long voieId);
}
