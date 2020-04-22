package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dao.SecteurRepository;
import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurLightDto;
import com.openclassrooms.escalade.dto.SecteurSaveDTto;
import com.openclassrooms.escalade.model.SecteurSearch;
import com.openclassrooms.escalade.services.SecteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/secteurs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SecteurController {

    private final SecteurService secteurService;

    @GetMapping
    @ResponseBody
    public Page<SecteurLightDto> getAllSecteurs(@RequestParam(required = false) String name,
                                                @RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "20") Integer size,
                                                @RequestParam(defaultValue = "name") String sortBy,
                                                @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                @RequestParam(defaultValue = "false") boolean unpaged) {
        SecteurSearch searchCriteria = new SecteurSearch(name);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return secteurService.findAll(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public SecteurDto getSecteur(@PathVariable Long id) {
        return secteurService.findById(id);
    }

    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public SecteurDto createSecteur(@RequestBody SecteurSaveDTto secteur) {
        return secteurService.create(secteur);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public SecteurDto updateSecteur(@RequestBody SecteurDto secteur) {
        return secteurService.update(secteur);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteSecteur(@PathVariable Long id) {
        try {
            secteurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
