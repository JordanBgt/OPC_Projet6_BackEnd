package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.repositories.CommentRepository;
import com.openclassrooms.escalade.services.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

}
