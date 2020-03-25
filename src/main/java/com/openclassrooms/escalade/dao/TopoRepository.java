package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long>, QuerydslPredicateExecutor<Topo> {
}
