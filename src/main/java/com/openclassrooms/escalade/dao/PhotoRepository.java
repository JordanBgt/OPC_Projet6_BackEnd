package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entity.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to handle Photo DAO operations
 */
@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
