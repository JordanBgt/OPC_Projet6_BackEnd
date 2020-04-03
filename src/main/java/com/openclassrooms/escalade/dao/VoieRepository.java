package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Voie;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoieRepository extends CrudRepository<Voie, Long>, QuerydslPredicateExecutor<Voie> {
}
