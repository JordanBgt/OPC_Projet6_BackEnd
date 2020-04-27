package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurLightDto;
import com.openclassrooms.escalade.dto.LongueurSaveDto;
import com.openclassrooms.escalade.model.LongueurSearch;
import com.openclassrooms.escalade.services.LongueurService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/longueurs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LongueurController {

    private final LongueurService longueurService;

    @GetMapping
    @ResponseBody
    public Page<LongueurLightDto> getAllLongueurs(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Long cotationMin,
                                                  @RequestParam(required = false) Long cotationMax,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "20") Integer size,
                                                  @RequestParam(defaultValue = "name") String sortBy,
                                                  @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                  @RequestParam(defaultValue = "false") boolean unpaged) {
        LongueurSearch searchCriteria = new LongueurSearch(name, cotationMin, cotationMax);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return longueurService.findAll(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public LongueurDto getLongueur(@PathVariable Long id) {
        return longueurService.findById(id);
    }

    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public LongueurDto createLongueur(@RequestBody LongueurSaveDto longueur) {
        return longueurService.create(longueur);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or #longueur.userId == #userId")
    public LongueurDto updateLongueur(@RequestBody LongueurDto longueur, @RequestParam Long userId) {
        return longueurService.update(longueur);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteLongueur(@PathVariable Long id) {
        longueurService.delete(id);
    }
}
