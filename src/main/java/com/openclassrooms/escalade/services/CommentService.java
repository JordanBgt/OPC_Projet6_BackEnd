package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.dto.CommentSaveDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentDto> findAllBySpotId(Long spotId, Pageable page);
    CommentDto findById(Long id);
    CommentDto create(CommentSaveDto comment);
    CommentDto update(CommentDto comment);
    void delete(Long id);

}
