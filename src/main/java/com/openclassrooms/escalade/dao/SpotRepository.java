package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Spot;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends CrudRepository<Spot, Long>, QuerydslPredicateExecutor<Spot> {
    List<Spot> findAllByUserId(Long userId);
}
