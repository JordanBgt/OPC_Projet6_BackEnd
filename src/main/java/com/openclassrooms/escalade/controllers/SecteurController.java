package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.entities.Secteur;
import com.openclassrooms.escalade.services.SecteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SecteurController {

    private final SecteurService secteurService;

    public SecteurController(SecteurService secteurService) {
        this.secteurService = secteurService;
    }

    @GetMapping("/secteurs")
    @ResponseBody
    public List<Secteur> getAllSecteurs() {
        return secteurService.findAll();
    }

    @GetMapping("/secteur/{id}")
    @ResponseBody
    public Secteur getSecteur(@PathVariable Long id) {
        return secteurService.findById(id);
    }

    @PostMapping("/secteur")
    @ResponseBody
    public Secteur createSecteur(@RequestBody Secteur secteur) {
        return secteurService.create(secteur);
    }

    @PutMapping("/secteur")
    @ResponseBody
    public Secteur updateSecteur(@RequestBody Secteur secteur) {
        return secteurService.update(secteur);
    }

    @DeleteMapping("/secteur/{id}")
    public ResponseEntity<Void> deleteSecteur(@PathVariable Long id) {
        try {
            secteurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
