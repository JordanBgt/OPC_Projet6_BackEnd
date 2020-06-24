package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoLightDto;
import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.model.TopoSearch;
import com.openclassrooms.escalade.services.TopoService;
import com.openclassrooms.escalade.services.TopoUserService;
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
@RequestMapping("/api/topos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class TopoController {

    private final TopoService topoService;
    private final TopoUserService topoUserService;

    @GetMapping
    @ResponseBody
    public Page<TopoLightDto> getAllTopos(@RequestParam(required = false) String country,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) Long cotationMin,
                                          @RequestParam(required = false) Long cotationMax,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size,
                                          @RequestParam(defaultValue = "name") String sortBy,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                          @RequestParam(defaultValue = "false") boolean unpaged) {
        log.info("Démarrage récupération de tous les topos");
        TopoSearch searchCriteria = new TopoSearch(country, name, cotationMin, cotationMax);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return topoService.findAll(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public TopoDto getTopo(@PathVariable Long id) {
        log.info("Démarrage récupération du topo avec l'id " + id);
        return topoService.findById(id);
    }

    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public TopoDto createTopo(@RequestBody TopoDto topo) {
        log.info("Démarrage création d'un topo");
        return null;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or #topo.creatorId == #userId")
    public TopoDto updateTopo(@RequestBody TopoDto topo, @RequestParam Long userId) {
        log.info("Démarrage modification d'un topo");
        return topoService.createOrUpdate(topo);
    }

    @PostMapping("/{id}/photos")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #topoCreatorId == #userId")
    public TopoDto createPhoto(@PathVariable Long id,
                               @RequestParam MultipartFile file,
                               @RequestParam Long topoCreatorId,
                               @RequestParam Long userId) {
        log.info("Démarrage upload photo");
        return this.topoService.addPhoto(id, file);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteTopo(@PathVariable Long id) {
        log.info("Démarrage suppression d'un topo");
        try {
            topoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/bookings")
    public TopoUserDto bookTopo(@RequestBody TopoUserDto topoUserDto) {
        log.info("Démarrage location d'un topo");
        return topoUserService.updateTopoUser(topoUserDto);
    }
}
