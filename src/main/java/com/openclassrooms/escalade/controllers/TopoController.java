package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoLightDto;
import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.entities.Topo;
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

/**
 * Controler to handle Topo
 *
 * @see Topo
 * @see TopoDto
 * @see TopoLightDto
 * @see TopoService
 * @see TopoUserService
 */
@RestController
@RequestMapping("/api/topos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class TopoController {

    private final TopoService topoService;
    private final TopoUserService topoUserService;

    /**
     * Method that returns a page of Topo
     * URL : localhost:8080/api/topos
     *
     * @param country search criteria if the user want a page of topos whose country corresponds to the given country
     * @param name search criteria if the user want a page of topos whose name corresponds to the given name
     * @param cotationMin search criteria if the user want a page of topos whose cotationMin corresponds to the given
     *                    cotationMin
     * @param cotationMax search criteria if the user want a page of topos whose cotationMax corresponds to the given
     *      *                    cotationMax
     * @param page page number requested. Default value : 0
     * @param size number of comments per page. Default value : 20
     * @param sortBy sorting criteria. Default value : name
     * @param direction sorting direction criteria. Default value : ASC
     * @param unpaged boolean which represents whether the user want a paginated result or not. Default value : false
     *
     * @return a page of TopoLightDto
     *
     * @see TopoSearch
     * @see TopoService#findAll(TopoSearch, Pageable)
     */
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

    /**
     * Method to get a Topo
     * URL : localhost:8080/api/topos/{id}
     * Only an admin or a connected user can request a Topo
     *
     * @param id id of the topo searched
     *
     * @return a TopoDto
     *
     * @see TopoService#findById(Long)
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public TopoDto getTopo(@PathVariable Long id) {
        log.info("Démarrage récupération du topo avec l'id " + id);
        return topoService.findById(id);
    }

    /**
     * Method to create a Topo
     * Only an admin or a connected user can create a Topo
     * URL : localhost:8080/api/topos
     *
     * @param topo the topo to save
     *
     * @return the topo saved
     *
     * @see TopoService#createOrUpdate(TopoDto)
     */
    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public TopoDto createTopo(@RequestBody TopoDto topo) {
        log.info("Démarrage création d'un topo");
        return topoService.createOrUpdate(topo);
    }

    /**
     * Method to update a Topo
     * URL : localhost:8080/api/topos/{id}
     * Only an admin or the user who created the topo can update it
     *
     * @param topo the topo updated to save
     * @param userId id of the requesting user
     *
     * @return the topo updated
     *
     * @see TopoService#createOrUpdate(TopoDto)
     */
    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or #topo.creatorId == #userId")
    public TopoDto updateTopo(@RequestBody TopoDto topo, @RequestParam Long userId) {
        log.info("Démarrage modification d'un topo");
        return topoService.createOrUpdate(topo);
    }

    /**
     * Method to add a photo to a Topo
     * URL : localhost:8080/api/topos/{id}/photos
     * Only an admin or the user who created the topo can add a photo
     *
     * @param id id of the topo
     * @param file photo to add
     * @param topoCreatorId id of the user who created the topo
     * @param userId id of the requesting user
     *
     * @return the topo wich we added the photo
     *
     * @see TopoService#addPhoto(Long, MultipartFile)
     */
    @PostMapping("/{id}/photos")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #topoCreatorId == #userId")
    public TopoDto createPhoto(@PathVariable Long id,
                               @RequestParam MultipartFile file,
                               @RequestParam Long topoCreatorId,
                               @RequestParam Long userId) {
        log.info("Démarrage upload photo");
        return this.topoService.addPhoto(id, file);
    }

    /**
     * Method to delete a Topo
     * URL : localhost:8080/api/topos/{id}
     * Only an admin can delete a Topo
     *
     * @param id id of the topo to delete
     *
     * @return ResponseEntity which represents the response to send : response without content if the deletion succeed
     * was successful, response with a NOT FOUND status if the deletion failed
     *
     * @see TopoService#delete(Long)
     */
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

    /**
     * Method to book a topo
     * URL : localhost:8080/api/topos/{id}/bookings
     * Only an admin or a connected user can book a topo
     *
     * @param topoUserDto a TopoUserDto which contains informations about the topo, the owner and the user who wants
     *                    to book it
     * @return a TopoUserDto
     *
     * @see TopoUserDto
     * @see TopoUserService#updateTopoUser(TopoUserDto)
     */
    @PostMapping("/{id}/bookings")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public TopoUserDto bookTopo(@RequestBody TopoUserDto topoUserDto) {
        log.info("Démarrage location d'un topo");
        return topoUserService.updateTopoUser(topoUserDto);
    }
}
