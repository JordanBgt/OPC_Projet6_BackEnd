package com.openclassrooms.escalade.repositories;

import org.springframework.data.repository.CrudRepository;
import com.openclassrooms.escalade.entities.Conseil;

public interface ConseilRepository extends CrudRepository<Conseil, Integer> {
}
