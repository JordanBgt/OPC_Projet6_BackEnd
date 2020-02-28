package com.openclassrooms.escalade.repositories;

import com.openclassrooms.escalade.entities.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
