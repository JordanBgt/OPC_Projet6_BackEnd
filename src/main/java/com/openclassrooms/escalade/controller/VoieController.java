package com.openclassrooms.escalade.controller;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieLightDto;
import com.openclassrooms.escalade.entity.Voie;
import com.openclassrooms.escalade.model.VoieSearch;
import com.openclassrooms.escalade.service.VoieService;
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
 * Controler to handle Voie
 *
 * @see Voie
 * @see VoieDto
 * @see VoieLightDto
 * @see VoieService
 */
@RestController
@RequestMapping("/api/voies")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class VoieController {

    private final VoieService voieService;

    /**
     * Method to get a page of Voie
     * URL : /api/voies
     *
     * @param name search criteria if the user want a page of voies whose name corresponds to the given name
     * @param secteurId search criteria if the user want a page of voies that belong to the secteurId given
     * @param cotationMin search criteria if the user want a page of voies whose cotationMin corresponds to the given
     *                    cotationMinId
     * @param cotationMax search criteria if the user want a page of voies whose cotationMax corresponds to the given
     *                    cotationMaxId
     * @param page page number requested. Default value : 0
     * @param size number of comments per page. Default value : 20
     * @param sortBy sorting criteria. Default value : name
     * @param direction sorting direction criteria. Default value : ASC
     * @param unpaged boolean which represents whether the user want a paginated result or not. Default value : false
     *
     * @return a page of VoieLightDto
     *
     * @see VoieSearch
     * @see VoieService#findAll(VoieSearch, Pageable)
     */
    @GetMapping
    public Page<VoieLightDto> getAllVoies(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) Long secteurId,
                                          @RequestParam(required = false) Long cotationMin,
                                          @RequestParam(required = false) Long cotationMax,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size,
                                          @RequestParam(defaultValue = "name") String sortBy,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                          @RequestParam(defaultValue = "false") boolean unpaged) {
        log.info("Start recovery of all voies");
        VoieSearch searchCriteria = new VoieSearch(name, cotationMin, cotationMax, secteurId);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return voieService.findAll(searchCriteria, pageable);
    }

    /**
     * Method to get a Voie
     * URL : /api/voies/{id}
     * Only an admin or a connected user can request a Voie
     * @param id of the Voie searched
     *
     * @return VoieDto
     *
     * @see VoieService#findById(Long)
     */
    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public VoieDto getVoie(@PathVariable Long id) {
        log.info("Start voie recovery");
        return voieService.findById(id);
    }

    /**
     * Method to create a Voie
     * URL : /api/voies
     * Only an admin or a connected user cans create a Voie
     *
     * @param voie Voie to save
     *
     * @return the Voie saved
     *
     * @see VoieService#createOrUpdate(VoieDto)
     */
    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public VoieDto createVoie(@RequestBody VoieDto voie) {
        log.info("Start voie creation");
        return voieService.createOrUpdate(voie);
    }

    /**
     * Method to update a Voie
     * URL : /api/voies/{id}
     * Only an admin or the user who created the voie can update id
     *
     * @param voie the voie updated to save
     *
     * @param userId id of the requesting user
     *
     * @return the voie updated
     *
     * @see VoieService#createOrUpdate(VoieDto)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #voie.userId == #userId")
    public VoieDto updateVoie(@RequestBody VoieDto voie, @RequestParam Long userId) {
        log.info("Start voie update");
        return voieService.createOrUpdate(voie);
    }

    /**
     * Method to delete a Voie
     * URL : /api/voies/{id}
     * Only an admin can delete a Voie
     *
     * @param id id of the voie to delete
     *
     * @return ResponseEntity which represents the response to send : response without content if the deletion succeed
     * was successful, response with a NOT FOUND status if the deletion failed
     *
     * @see VoieService#delete(Long)
     */
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteVoie(@PathVariable Long id) {
        log.info("Start voie deletion");
        try {
            voieService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
