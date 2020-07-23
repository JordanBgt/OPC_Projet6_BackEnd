package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
import com.openclassrooms.escalade.entities.Spot;
import com.openclassrooms.escalade.model.SpotSearch;
import com.openclassrooms.escalade.services.SpotService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

/**
 * Controler to handle Spot
 *
 * @see Spot
 * @see SpotDto
 * @see SpotLightDto
 * @see SpotService
 */
@RestController
@RequestMapping("/api/spots")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class SpotController {

    private final SpotService spotService;

    /**
     * Method to get a page of Spot
     * URL : localhost:8080/api/spots
     *
     * @param name search criteria if the user want a page of spots whose name corresponds to the given name
     * @param country search criteria if the user want a page of spots whose country corresponds to the given country
     * @param city search criteria if the user want a page of spots whose city corresponds to the given city
     * @param isOfficial search criteria if the user want a page of spots tha have been approved by Les amis de l'escalade
     * @param cotationMin search criteria if the user want a page of spots whose cotationMin corresponds to the
     *                    given cotationMinId
     * @param cotationMax search criteria if the user want a page of spots whose cotationMax corresponds to the
     *                    given cotationMaxId
     * @param page page number requested. Default value : 0
     * @param size number of comments per page. Default value : 20
     * @param sortBy sorting criteria. Default value : name
     * @param direction sorting direction criteria. Default value : ASC
     * @param unpaged boolean which represents whether the user want a paginated result or not. Default value : false
     *
     * @return a page of SpotLightDto
     *
     * @see SpotService#findAll(SpotSearch, Pageable)
     */
    @GetMapping
    @ResponseBody
    public Page<SpotLightDto> getAllSpots(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String country,
                                          @RequestParam(required = false) String city,
                                          @RequestParam(required = false) boolean isOfficial,
                                          @RequestParam(required = false) Long cotationMin,
                                          @RequestParam(required = false) Long cotationMax,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size,
                                          @RequestParam(defaultValue = "name") String sortBy,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                          @RequestParam(defaultValue = "false") boolean unpaged) {
        log.info("Démarrage récupération de tous les spots");
        SpotSearch searchCriteria = new SpotSearch(name, country, city, isOfficial, cotationMin, cotationMax);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return spotService.findAll(searchCriteria, pageable);
    }

    /**
     * Method to get a Spot
     * URL : localhost:8080/api/spots/{id}
     * Only an admin or a connected user can request a Spot
     *
     * @param id id of the spot searched
     *
     * @return a SpotDto
     *
     * @see SpotService#findById(Long)
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public SpotDto getSpot(@PathVariable Long id) {
        log.info("Démarrage récupération du spot avec l'id " + id);
        return spotService.findById(id);
    }

    /**
     * Method to create a Spot
     * URL : localhost:8080/api/spots
     * Only an admin or a connected user can create a Spot
     *
     * @param spot the spot to save
     *
     * @return the spot saved
     *
     * @see SpotService#createOrUpdate(SpotDto)
     */
    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public SpotDto createSpot(@RequestBody SpotDto spot) {
        log.info("Démarrage création d'un spot");
        return spotService.createOrUpdate(spot);
    }

    /**
     * Method to update a Spot
     * URL : localhost:8080/api/spots/{id}
     * Only an admin or the user who created the spot can update it
     *
     * @param spot the spot updated to save
     * @param userId id of the requesting user
     *
     * @return the spot updated
     *
     * @see SpotService#createOrUpdate(SpotDto)
     */
    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or #spot.userId == #userId")
    public SpotDto updateSpot(@RequestBody SpotDto spot, @RequestParam Long userId) {
        log.info("Démarrage modification d'un spot");
        return spotService.createOrUpdate(spot);
    }

    /**
     * Method to add a photo to a spot
     * URL : localhost:8080/api/spots/{id}/photos
     * Only an admin or the user who created the spot can add a photo
     *
     * @param id id of the spot
     * @param file photo to add
     * @param spotUserId id of the user who created the spot
     * @param userId id of the requesting user
     *
     * @return the spot to which we added the photo
     *
     * @see SpotService#addPhoto(Long, MultipartFile)
     */
    @PostMapping("/{id}/photos")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #spotUserId == #userId")
    public SpotDto addPhoto(@PathVariable Long id,
                            @RequestParam MultipartFile file,
                            @RequestParam Long spotUserId,
                            @RequestParam Long userId) {
        log.info("Démarrage upload photo");
        return this.spotService.addPhoto(id, file);
    }

    /**
     * Method to delete a Spot
     * URL : localhost:8080/api/spots/{id}
     * Only an admin can delete a Spot
     *
     * @param id id of the spot to delete
     *
     * @return ResponseEntity which represents the response to send : response without content if the deletion succeed
     * was successful, response with a NOT FOUND status if the deletion failed
     *
     * @see SpotService#delete(Long)
     */
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long id) {
        log.info("Démarrage suppression d'un topo");
        try {
            spotService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
