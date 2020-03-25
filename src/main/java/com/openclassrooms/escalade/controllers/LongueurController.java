package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurSaveDto;
import com.openclassrooms.escalade.services.LongueurService;
import lombok.RequiredArgsConstructor;
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
    public List<LongueurDto> getAllLongueurs() {
        return longueurService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public LongueurDto getLongueur(@PathVariable Long id) {
        return longueurService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public LongueurDto createLongueur(@RequestBody LongueurSaveDto longueur) {
        return longueurService.create(longueur);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public LongueurDto updateLongueur(@RequestBody LongueurSaveDto longueur,
                                      @PathVariable Long id) {
        return longueurService.update(longueur, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLongueur(@PathVariable Long id) {
        longueurService.delete(id);
    }
}
