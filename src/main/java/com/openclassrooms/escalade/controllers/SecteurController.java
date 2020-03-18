package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.SecteurDto;
import com.openclassrooms.escalade.dto.SecteurSaveDTto;
import com.openclassrooms.escalade.services.SecteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/secteurs")
@CrossOrigin(origins = "*")
public class SecteurController {

    private final SecteurService secteurService;

    public SecteurController(SecteurService secteurService) {
        this.secteurService = secteurService;
    }

    @GetMapping
    @ResponseBody
    public List<SecteurDto> getAllSecteurs() {
        return secteurService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SecteurDto getSecteur(@PathVariable Long id) {
        return secteurService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public SecteurDto createSecteur(@RequestBody SecteurSaveDTto secteur) {
        return secteurService.create(secteur);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public SecteurDto updateSecteur(@RequestBody SecteurSaveDTto secteur,
                                    @PathVariable Long id) {
        return secteurService.update(secteur, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecteur(@PathVariable Long id) {
        try {
            secteurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
