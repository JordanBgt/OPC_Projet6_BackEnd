package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dao.predicate.SpotPredicateBuilder;
import com.openclassrooms.escalade.dto.*;
import com.openclassrooms.escalade.entity.*;
import com.openclassrooms.escalade.mapper.SpotMapper;
import com.openclassrooms.escalade.model.SpotSearch;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Service to manage Spot
 *
 * @see SpotDto
 * @see SpotLightDto
 * @see SpotMapper
 * @see SpotRepository
 */
@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;
    private final UserRepository userRepository;
    private final CotationRepository cotationRepository;
    private final SecteurRepository secteurRepository;
    private final SecteurService secteurService;
    private final FileStorageService fileStorageService;
    private final PhotoRepository photoRepository;

    /**
     * Method to retrieve all the spots. For each spot, we convert the photo files to base 64.
     *
     * @param searchCriteria search criteria
     * @param page pagination criteria
     *
     * @return page of SpotLightDto
     *
     * @see SpotSearch
     * @see PhotoDto#convertFileToBase64String(Resource)
     * @see SpotPredicateBuilder
     * @see SpotRepository#findAll(Predicate, Pageable)
     */
    public Page<SpotLightDto> findAll (SpotSearch searchCriteria, Pageable page) {
        Page<SpotLightDto> results = spotRepository.findAll(SpotPredicateBuilder.buildSearch(searchCriteria), page).map(spotMapper::toSpotLightDto);
        results.forEach(spot -> {
            if (spot.getPhotos() != null && spot.getPhotos().size() > 0) {
                spot.getPhotos().forEach(photoDto -> {
                    photoDto.convertFileToBase64String(fileStorageService.load(photoDto.getName()));
                });
            }
        });
        return results;
    }

    /**
     * Method to get a Spot by its id
     *
     * @param id id of the requested spot
     *
     * @return SpotDto
     *
     * @see this#getPhotosToBase64(SpotDto)
     * @see SpotRepository#findById(Object)
     * @see EntityNotFoundException
     */
    public SpotDto findById(Long id) {
        SpotDto spot = spotMapper.toSpotDto(spotRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        this.getPhotosToBase64(spot);
        return spot;
    }

    /**
     * Method to create or update a Spot
     *
     * @param spotDto the spot to save
     *
     * @return SpotDto saved
     *
     * @see this#getPhotosToBase64(SpotDto)
     * @see SpotRepository#save(Object)
     * @see EntityNotFoundException
     */
    public SpotDto createOrUpdate(SpotDto spotDto) {
        User user = userRepository.findById(spotDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(spotDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(spotDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        List<Photo> photos = new ArrayList<>();
        if(spotDto.getPhotos() != null && spotDto.getPhotos().size() > 0) {
            spotDto.getPhotos().forEach(photoDto -> photos.add(photoRepository.findById(photoDto.getId())
                    .orElseThrow(EntityNotFoundException::new)));
        }
        Spot spot = Spot.builder()
                .id(spotDto.getId())
                .name(spotDto.getName())
                .city(spotDto.getCity())
                .country(spotDto.getCountry())
                .description(spotDto.getDescription())
                .user(user)
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .isOfficial(spotDto.isOfficial())
                .photos(photos)
                .build();
        SpotDto result = spotMapper.toSpotDto(spotRepository.save(spot));
        this.getPhotosToBase64(result);
        return result;
    }

    /**
     * Method to add a photo to a spot
     *
     * @param spotId id of the spot to which we want to add a photo
     * @param file MultipartFile which represents the photo
     *
     * @return SpotDto
     *
     * @see UUID#randomUUID()
     * @see FileStorageService#save(MultipartFile, String)
     * @see this#getPhotosToBase64(SpotDto)
     * @see SpotRepository#save(Object)
     * @see EntityNotFoundException
     */
    public SpotDto addPhoto(Long spotId, MultipartFile file) {
        Spot spot = spotRepository.findById(spotId).orElseThrow(EntityNotFoundException::new);
        String extension = Objects.requireNonNull(file.getContentType()).substring(file.getContentType().indexOf('/')+1);
        Photo photo = Photo.builder()
            .name("photo" + UUID.randomUUID() + "." + extension)
            .extension(extension)
            .build();
        try {
            fileStorageService.save(file, photo.getName());
            spot.getPhotos().add(photo);
            SpotDto result = spotMapper.toSpotDto(spotRepository.save(spot));
            this.getPhotosToBase64(result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Method to delete a Spot. We must first remove the secteurs and the photo files linked to the Spot to delete
     *
     * @param id id of the spot to delete
     *
     * @see SecteurRepository#findAllBySpotId(Long)
     * @see FileStorageService#delete(String)
     * @see SpotRepository#delete(Object)
     * @see EntityNotFoundException
     */
    public void delete(Long id) {
        List<Long> secteursId = secteurRepository.findAllBySpotId(id);
        if (secteursId != null) {
            for (Long secteurId: secteursId) {
                secteurService.delete(secteurId);
            }
        }
        Spot spot = spotRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (spot.getPhotos() != null && spot.getPhotos().size() > 0) {
            spot.getPhotos().forEach(photo -> this.fileStorageService.delete(photo.getName()));
        }
        spotRepository.delete(spot);
    }

    /**
     * Method to convert photo files to base 64 in order to send them to the client
     *
     * @param spot the spot for which we want to convert the photo files
     *
     * @see PhotoDto#convertFileToBase64String(Resource)
     * @see FileStorageService#load(String)
     */
    private void getPhotosToBase64(SpotDto spot) {
        if (spot.getPhotos() != null && spot.getPhotos().size() > 0) {
            spot.getPhotos().forEach(element ->
                    element.convertFileToBase64String(fileStorageService.load(element.getName())));
        }
    }
}
