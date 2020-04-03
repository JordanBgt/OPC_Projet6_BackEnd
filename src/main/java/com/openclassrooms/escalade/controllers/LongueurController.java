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
                                                  @RequestParam(required = false) Long cotationMinId,
                                                  @RequestParam(required = false) Long cotationMaxId,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "20") Integer size,
                                                  @RequestParam(defaultValue = "name") String sortBy,
                                                  @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                  @RequestParam(defaultValue = "false") boolean unpaged) {
        LongueurSearch searchCriteria = new LongueurSearch(name, cotationMinId, cotationMaxId);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return longueurService.findAll(searchCriteria, pageable);
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
