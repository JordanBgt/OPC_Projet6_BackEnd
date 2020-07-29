package com.openclassrooms.escalade.controller;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurLightDto;
import com.openclassrooms.escalade.entity.Secteur;
import com.openclassrooms.escalade.model.SecteurSearch;
import com.openclassrooms.escalade.service.SecteurService;
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
 * Controler to handle Secteur
 *
 * @see Secteur
 * @see SecteurDto
 * @see SecteurLightDto
 * @see SecteurService
 */
@RestController
@RequestMapping("/api/secteurs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class SecteurController {

    private final SecteurService secteurService;

    /**
     * Method to get a page of Secteur
     * URL : /api/secteurs
     *
     * @param name search criteria if the user want a page of secteurs whose name corresponds to the given name
     * @param spotId search criteria if the user want a page of secteurs that belong to the spot given
     * @param page page number requested. Default value : 0
     * @param size number of comments per page. Default value : 20
     * @param sortBy sorting criteria. Default value : name
     * @param direction sorting direction criteria. Default value : ASC
     * @param unpaged boolean which represents whether the user want a paginated result or not. Default value : false
     *
     * @return a page of SecteurLightDto
     *
     * @see SecteurSearch
     * @see SecteurService#findAll(SecteurSearch, Pageable)
     */
    @GetMapping
    @ResponseBody
    public Page<SecteurLightDto> getAllSecteurs(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) Long spotId,
                                                @RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "20") Integer size,
                                                @RequestParam(defaultValue = "name") String sortBy,
                                                @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                @RequestParam(defaultValue = "false") boolean unpaged) {
        log.info("Start recovery of all secteurs");
        SecteurSearch searchCriteria = new SecteurSearch(name, spotId);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return secteurService.findAll(searchCriteria, pageable);
    }

    /**
     * Method to get a Secteur
     * URL : /api/secteurs/{id}
     * Only an admin or a connected user can request a secteur
     *
     * @param id id of the secteur searched
     *
     * @return a SecteurDto
     *
     * @see SecteurService#findById(Long)
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public SecteurDto getSecteur(@PathVariable Long id) {
        log.info("Start secteur recovery");
        return secteurService.findById(id);
    }

    /**
     * Method to create a Secteur
     * URL : /api/secteurs
     * Only an admin or a connected user can create a Secteur
     *
     * @param secteur the secteur to save
     *
     * @return the secteur saved
     *
     * @see SecteurService#createOrUpdate(SecteurDto)
     */
    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public SecteurDto createSecteur(@RequestBody SecteurDto secteur) {
        log.info("Start secteur creation");
        return secteurService.createOrUpdate(secteur);
    }

    /**
     * Method to update a Secteur
     * URL : /api/secteurs/{id}
     * Only an admin or the user who created the secteur can update it
     *
     * @param secteur the secteur updated to save
     * @param userId id of the requesting user
     *
     * @return the secteur updated
     *
     * @see SecteurService#createOrUpdate(SecteurDto)
     */
    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or #secteur.userId == #userId")
    public SecteurDto updateSecteur(@RequestBody SecteurDto secteur, @RequestParam Long userId) {
        log.info("Start secteur update");
        return secteurService.createOrUpdate(secteur);
    }

    /**
     * Method to delete a Secteur
     * URL : /api/secteurs/{id}
     * Only an admin can delete a Secteur
     *
     * @param id id of the secteur to delete
     *
     * @return ResponseEntity which represents the response to send : response without content if the deletion succeed
     * was successful, response with a NOT FOUND status if the deletion failed
     *
     * @see SecteurService#delete(Long)
     */
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteSecteur(@PathVariable Long id) {
        log.info("Start secteur deletion");
        try {
            secteurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
