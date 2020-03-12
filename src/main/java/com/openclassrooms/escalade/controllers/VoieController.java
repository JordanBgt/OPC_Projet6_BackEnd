package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.services.VoieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VoieController {

    private final VoieService voieService;

    public VoieController(VoieService voieService) {
        this.voieService = voieService;
    }

    @GetMapping("/voies")
    @ResponseBody
    public List<VoieDto> getAllVoies() {
        return voieService.findAll();
    }

    @GetMapping("/voie/{id}")
    @ResponseBody
    public VoieDto getVoie(@PathVariable Long id) {
        return voieService.findById(id);
    }

    @PostMapping("/voie")
    @ResponseBody
    public VoieDto createVoie(@RequestBody VoieDto voie) {
        return voieService.create(voie);
    }

    @PutMapping("/voie/{id}")
    @ResponseBody
    public VoieDto updateVoie(@RequestBody VoieDto voie,
                              @PathVariable Long id) {
        return voieService.update(voie, id);
    }

    @DeleteMapping("/voie/{id}")
    public ResponseEntity<Void> deleteVoie(@PathVariable Long id) {
        try {
            voieService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
