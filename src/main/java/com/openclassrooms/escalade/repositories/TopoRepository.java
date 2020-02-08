package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TopoRepository extends JpaRepository<Topo, Long> {
}
