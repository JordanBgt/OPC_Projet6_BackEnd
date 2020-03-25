package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.CotationDto;
import com.openclassrooms.escalade.services.CotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cotations")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CotationController {

    private final CotationService cotationService;

    @GetMapping
    @ResponseBody
    public List<CotationDto> getAllCotations() {
        return this.cotationService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CotationDto getCotation(@PathVariable Long id) {
        return this.cotationService.findById(id);
    }
}
