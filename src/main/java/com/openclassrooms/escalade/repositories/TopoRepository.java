package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {
}
