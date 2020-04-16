package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.dto.CommentSaveDto;
import com.openclassrooms.escalade.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    @ResponseBody
    public Page<CommentDto> getAllCommentsBySpot(@RequestParam Long spotId,
                                                 @RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "20") Integer size,
                                                 @RequestParam(defaultValue = "date") String sortBy,
                                                 @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                 @RequestParam(defaultValue = "false") boolean unpaged) {
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return commentService.findAllBySpotId(spotId, pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CommentDto getComment(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public CommentDto createComment(@RequestBody CommentSaveDto comment) {
        return commentService.create(comment);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public CommentDto updateComment(@RequestBody CommentDto comment) {
        return commentService.update(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            commentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return  ResponseEntity.notFound().build();
        }
    }
}
