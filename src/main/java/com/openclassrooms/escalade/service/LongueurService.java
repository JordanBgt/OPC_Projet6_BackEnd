package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dao.predicate.LongueurPredicateBuilder;
import com.openclassrooms.escalade.dto.LongueurDto;
import com.openclassrooms.escalade.dto.LongueurLightDto;
import com.openclassrooms.escalade.entity.Cotation;
import com.openclassrooms.escalade.entity.Longueur;
import com.openclassrooms.escalade.entity.User;
import com.openclassrooms.escalade.entity.Voie;
import com.openclassrooms.escalade.mapper.LongueurMapper;
import com.openclassrooms.escalade.model.LongueurSearch;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Service to manage Longueur
 *
 * @see LongueurDto
 * @see LongueurLightDto
 * @see LongueurRepository
 * @see LongueurMapper
 */
@Service
@RequiredArgsConstructor
public class LongueurService {

    private final LongueurRepository longueurRepository;
    private final LongueurMapper longueurMapper;
    private final CotationRepository cotationRepository;
    private final UserRepository userRepository;
    private final VoieRepository voieRepository;

    /**
     * Method to retrieve all the longueurs
     *
     * @param searchCriteria search criteria
     * @param page pagination criteria
     *
     * @return a page of LongueurLightDto
     *
     * @see LongueurSearch
     * @see LongueurPredicateBuilder
     * @see LongueurRepository#findAll(Predicate, Pageable)
     */
    public Page<LongueurLightDto> findAll(LongueurSearch searchCriteria, Pageable page) {
        return longueurRepository.findAll(LongueurPredicateBuilder.buildSearch(searchCriteria), page).map(longueurMapper::toLongueurLightDto);
    }

    /**
     * Method to get a longueur by its id
     *
     * @param id id of the requested longueur
     *
     * @return LongueurDto
     *
     * @see LongueurRepository#findById(Object)
     * @see EntityNotFoundException
     */
    public LongueurDto findById(Long id) {
        return longueurMapper.toLongueurDto(longueurRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Method to create or update a longueur
     *
     * @param longueurDto the longueur to save
     *
     * @return LongueurDto saved
     *
     * @see LongueurRepository#save(Object)
     * @see EntityNotFoundException
     */
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

    /**
     * Method to delete a longueur
     *
     * @param id id of the longueur to delete
     *
     * @see LongueurRepository#delete(Object)
     * @see EntityNotFoundException
     */
    public void delete(Long id) {
        Longueur longueur = longueurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        longueurRepository.delete(longueur);
    }
}
