package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurLightDto;
import com.openclassrooms.escalade.entities.Longueur;
import com.openclassrooms.escalade.model.LongueurSearch;
import com.openclassrooms.escalade.services.LongueurService;
import lombok.RequiredArgsConstructor;
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
 * Controler to handle Longueur
 *
 * @see Longueur
 * @see LongueurDto
 * @see LongueurLightDto
 * @see LongueurService
 */
@RestController
@RequestMapping("/api/longueurs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LongueurController {

    private final LongueurService longueurService;

    /**
     * Method that returns a page of Longueur
     * URL : localhost:8080/api/longueurs
     *
     * @param name search criteria if the user want a page of longueurs whose name corresponds to the given name
     * @param voieId search criteria if the user want a page of longueurs that belong to the voie given
     * @param cotationMin search criteria if the user want a page of longueurs whose cotationMin corresponds to the
     *                    given cotationMinId
     * @param cotationMax search criteria if the user want a page of longueurs whose cotationMax corresponds to the
     *                    given cotationMaxId
     * @param page page number requested. Default value : 0
     * @param size number of comments per page. Default value : 20
     * @param sortBy sorting criteria. Default value : name
     * @param direction sorting direction criteria. Default value : ASC
     * @param unpaged boolean which represents whether the user want a paginated result or not. Default value : false
     *
     * @return a page of LongueurLightDto
     *
     * @see LongueurSearch
     * @see LongueurService#findAll(LongueurSearch, Pageable)
     */
    @GetMapping
    @ResponseBody
    public Page<LongueurLightDto> getAllLongueurs(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Long voieId,
                                                  @RequestParam(required = false) Long cotationMin,
                                                  @RequestParam(required = false) Long cotationMax,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "20") Integer size,
                                                  @RequestParam(defaultValue = "name") String sortBy,
                                                  @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                  @RequestParam(defaultValue = "false") boolean unpaged) {
        LongueurSearch searchCriteria = new LongueurSearch(name, cotationMin, cotationMax, voieId);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return longueurService.findAll(searchCriteria, pageable);
    }

    /**
     * Method to get a Longueur
     * URL : localhost:8080/api/longueurs/{id}
     * Only an admin or a connected user can request a Longueur
     *
     * @param id id of the longueur searched
     *
     * @return a LongueurDto
     *
     * @see LongueurService#findById(Long)
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public LongueurDto getLongueur(@PathVariable Long id) {
        return longueurService.findById(id);
    }

    /**
     * Method to create a Longueur
     * Only an admin or a conneted user can create a Longueur
     * URL : localhost:8080/api/longueurs
     *
     * @param longueur the longueur to save
     *
     * @return the longueur saved
     *
     * @see LongueurService#createOrUpdate(LongueurDto)
     */
    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public LongueurDto createLongueur(@RequestBody LongueurDto longueur) {
        return longueurService.createOrUpdate(longueur);
    }

    /**
     * Method to update a Longueur
     * URL : localhost:8080/api/longueurs/{id}
     * Only an admin or the user who created the longueur can update it
     *
     * @param longueur longueur updated to save
     * @param userId id of the requesting user
     *
     * @return the longueur updated
     *
     * @see LongueurService#createOrUpdate(LongueurDto)
     */
    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or #longueur.userId == #userId")
    public LongueurDto updateLongueur(@RequestBody LongueurDto longueur, @RequestParam Long userId) {
        return longueurService.createOrUpdate(longueur);
    }

    /**
     * Method to delete a Longueur
     * URL : localhost:8080/api/longueurs/{id}
     * Only an admin can delete a Longueur
     *
     * @param id id of the longueur to delete
     *
     * @return ResponseEntity which represents the response to send : response without content if the deletion succeed
     * was successful, response with a NOT FOUND status if the deletion failed
     *
     * @see LongueurService#delete(Long)
     */
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteLongueur(@PathVariable Long id) {
        try {
            longueurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
