package com.openclassrooms.escalade.services.impl;

import com.openclassrooms.escalade.dao.SpotRepository;
import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.dto.CommentSaveDto;
import com.openclassrooms.escalade.entities.Comment;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.mapper.CommentMapper;
import com.openclassrooms.escalade.dao.CommentRepository;
import com.openclassrooms.escalade.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final SpotRepository spotRepository;


    @Override
    public Page<CommentDto> findAllBySpotId(Long spotId, Pageable page) {
        return commentRepository.findAllBySpotId(spotId, page).map(commentMapper::toCommentDto);
    }

    @Override
    public CommentDto findById(Long id) {
        return commentMapper.toCommentDto(commentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public CommentDto create(CommentSaveDto commentSaveDto) {
        User user = userRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        Spot spot = spotRepository.findById(commentSaveDto.getSpotId()).orElseThrow(EntityNotFoundException::new);
        LocalDateTime date = LocalDateTime.now();
        Comment comment = Comment.builder()
                .user(user)
                .spot(spot)
                .content(commentSaveDto.getContent())
                .date(date)
                .build();
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(EntityNotFoundException::new);
        comment.setContent(commentDto.getContent());
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        commentRepository.delete(comment);
    }
}
