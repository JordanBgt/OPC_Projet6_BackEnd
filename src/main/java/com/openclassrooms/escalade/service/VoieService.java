package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dao.predicate.VoiePredicateBuilder;
import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieLightDto;
import com.openclassrooms.escalade.entity.*;
import com.openclassrooms.escalade.mapper.VoieMapper;
import com.openclassrooms.escalade.model.VoieSearch;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service to manage Voie
 *
 * @see VoieDto
 * @see VoieLightDto
 * @see VoieMapper
 * @see VoieRepository
 */
@Service
@RequiredArgsConstructor
public class VoieService {

    private final VoieRepository voieRepository;
    private final VoieMapper voieMapper;
    private final CotationRepository cotationRepository;
    private final UserRepository userRepository;
    private final SecteurRepository secteurRepository;
    private final LongueurRepository longueurRepository;
    private final LongueurService longueurService;

    /**
     * Method to retrieve all the voies
     *
     * @param searchCriteria search criteria
     * @param page pagination criteria
     *
     * @return page of VoieLightDto
     *
     * @see VoieSearch
     * @see VoiePredicateBuilder
     * @see VoieRepository#findAll(Predicate, Pageable)
     */
    public Page<VoieLightDto> findAll(VoieSearch searchCriteria, Pageable page) {
        return voieRepository.findAll(VoiePredicateBuilder.buildSearch(searchCriteria), page).map(voieMapper::toVoieLightDto);
    }

    /**
     * Method to get a Voie by its id
     *
     * @param id id of the requested voie
     *
     * @return VoieDto
     *
     * @see VoieRepository#findById(Object)
     * @see EntityNotFoundException
     */
    public VoieDto findById(Long id) {
        return voieMapper.toVoieDto(voieRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Method to create or update a Voie
     *
     * @param voieDto voie updated to save
     *
     * @return VoitDto saved
     *
     * @see VoieRepository#save(Object)
     * @see EntityNotFoundException
     */
    public VoieDto createOrUpdate(VoieDto voieDto) {
        Cotation cotationMin = cotationRepository.findById(voieDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(voieDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(voieDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Secteur secteur = secteurRepository.findById(voieDto.getSecteurId()).orElseThrow(EntityNotFoundException::new);
        Voie voie = Voie.builder()
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .name(voieDto.getName())
                .user(user)
                .description(voieDto.getDescription())
                .id(voieDto.getId())
                .secteur(secteur)
                .build();
        return voieMapper.toVoieDto(voieRepository.save(voie));
    }

    /**
     * Method to delete a Voie. We must first remove the longueurslinked to the Voie to delete
     *
     * @param id id of the voie to delete
     *
     * @see VoieRepository#delete(Object)
     * @see EntityNotFoundException
     */
    public void delete(Long id) {
        List<Long> longueursId = longueurRepository.findAllByVoieId(id);
        if (longueursId != null) {
            for (Long longueurId: longueursId) {
                longueurService.delete(longueurId);
            }
        }
        Voie voie = voieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        voieRepository.delete(voie);
    }
}
