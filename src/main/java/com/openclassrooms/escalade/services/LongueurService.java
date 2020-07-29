package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dao.predicate.LongueurPredicateBuilder;
import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurLightDto;
import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.Longueur;
import com.openclassrooms.escalade.entities.User;
import com.openclassrooms.escalade.entities.Voie;
import com.openclassrooms.escalade.mapper.LongueurMapper;
import com.openclassrooms.escalade.model.LongueurSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class LongueurService {

    private final LongueurRepository longueurRepository;
    private final LongueurMapper longueurMapper;
    private final CotationRepository cotationRepository;
    private final UserRepository userRepository;
    private final VoieRepository voieRepository;

    public Page<LongueurLightDto> findAll(LongueurSearch searchCriteria, Pageable page) {
        return longueurRepository.findAll(LongueurPredicateBuilder.buildSearch(searchCriteria), page).map(longueurMapper::toLongueurLightDto);
    }

    public LongueurDto findById(Long id) {
        return longueurMapper.toLongueurDto(longueurRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public LongueurDto createOrUpdate(LongueurDto longueurDto) {
        Cotation cotationMin = cotationRepository.findById(longueurDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(longueurDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(longueurDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Voie voie = voieRepository.findById(longueurDto.getVoieId()).orElseThrow(EntityNotFoundException::new);
        Longueur longueur = Longueur.builder()
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .name(longueurDto.getName())
                .user(user)
                .description(longueurDto.getDescription())
                .id(longueurDto.getId())
                .voie(voie)
                .build();
        return longueurMapper.toLongueurDto(longueurRepository.save(longueur));
    }

    public void delete(Long id) {
        Longueur longueur = longueurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        longueurRepository.delete(longueur);
    }
}
