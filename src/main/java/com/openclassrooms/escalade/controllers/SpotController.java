package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotLightDto;
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

@RestController
@RequestMapping("/api/spots")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class SpotController {

    private final SpotService spotService;

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

    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public SpotDto getSpot(@PathVariable Long id) {
        log.info("Démarrage récupération du spot avec l'id " + id);
        return spotService.findById(id);
    }

    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public SpotDto createSpot(@RequestBody SpotDto spot) {
        log.info("Démarrage création d'un spot");
        return spotService.createOrUpdate(spot);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or #spot.userId == #userId")
    public SpotDto updateSpot(@RequestBody SpotDto spot, @RequestParam Long userId) {
        log.info("Démarrage modification d'un spot");
        return spotService.createOrUpdate(spot);
    }

    @PostMapping("/{id}/photos")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #spotUserId == #userId")
    public SpotDto addPhoto(@PathVariable Long id,
                            @RequestParam MultipartFile file,
                            @RequestParam Long spotUserId,
                            @RequestParam Long userId) {
        log.info("Démarrage upload photo");
        return this.spotService.addPhoto(id, file);
    }

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
