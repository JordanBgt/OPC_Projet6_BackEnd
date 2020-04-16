package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Page<Comment> findAllBySpotId(Long spotId, Pageable pageable);
}
