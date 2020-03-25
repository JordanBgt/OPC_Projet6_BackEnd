package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.mapper.CommentMapper;
import com.openclassrooms.escalade.dao.CommentRepository;
import com.openclassrooms.escalade.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

}
