package com.openclassrooms.escalade.controller;

import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.dto.CommentSaveDto;
import com.openclassrooms.escalade.entity.Comment;
import com.openclassrooms.escalade.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

/**
 * Controler to handle comments
 *
 * @see Comment
 * @see CommentDto
 * @see CommentSaveDto
 * @see CommentService
 */
@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    /**
     * Method that returns a page of comments for the given spot.
     * URL : /api/comments
     * Only an administrator or a connected user can accessed to comments
     *
     * @param spotId id of the spot for which we are looking for comments
     * @param page page number requested. Default value : 0
     * @param size number of comments per page. Default value : 20
     * @param sortBy sorting criteria. Default value : date
     * @param direction sorting direction criteria. Default value : DESC
     * @param unpaged boolean which represents whether the user want a paginated result or not. Default value : false
     *
     * @return a page with spot's comments
     *
     * @see CommentService#findAllBySpotId(Long, Pageable)
     */
    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Page<CommentDto> getAllCommentsBySpot(@RequestParam Long spotId,
                                                 @RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "20") Integer size,
                                                 @RequestParam(defaultValue = "date") String sortBy,
                                                 @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                 @RequestParam(defaultValue = "false") boolean unpaged) {
        log.info("Start recovery of all comments");
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return commentService.findAllBySpotId(spotId, pageable);
    }

    /**
     * Method to get a comment
     * URL : /api/comments/{id}
     * Only an administrator or a connected user can request a comment
     *
     * @param id id of the comment searched
     *
     * @return a comment
     *
     * @see CommentService#findById(Long)
     */
    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public CommentDto getComment(@PathVariable Long id) {
        log.info("Start comment recovery");
        return commentService.findById(id);
    }

    /**
     * Method to post a comment
     * URL : /api/comments
     * Only an administrator or a connected user can post a comment
     *
     * @param comment the comment to save
     *
     * @return the comment saved
     *
     * @see CommentService#create(CommentSaveDto)
     */
    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public CommentDto createComment(@RequestBody CommentSaveDto comment) {
        log.info("Starting creation of a comment");
        return commentService.create(comment);
    }

    /**
     * Method to update an existing comment
     * URL : /api/comments/{id}
     * Only an administrator or the user who posted the comment can update it
     *
     * @param comment the comment updated to save
     * @param userId user id for security checks
     *
     * @return the comment updated and saved
     *
     * @see CommentService#update(CommentDto)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #comment.userId == #userId")
    public CommentDto updateComment(@RequestBody CommentDto comment, @RequestParam Long userId) {
        log.info("Start comment update");
        return commentService.update(comment);
    }

    /**
     * Method to delete a comment
     * URL : /api/comments/{id}
     * Only an administrator can delete a comment
     *
     * @param id the id of the comment to delete
     *
     * @return a response entity with a status representing the success or failure of the deletion
     *
     * @see CommentService#delete(Long)
     */
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        log.info("Start comment deletion");
        try {
            commentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return  ResponseEntity.notFound().build();
        }
    }
}
