package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}
