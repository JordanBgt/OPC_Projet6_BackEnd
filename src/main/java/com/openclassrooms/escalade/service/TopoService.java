package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dao.*;
import com.openclassrooms.escalade.dao.predicate.TopoPredicateBuilder;
import com.openclassrooms.escalade.dto.PhotoDto;
import com.openclassrooms.escalade.dto.TopoDto;
import com.openclassrooms.escalade.dto.TopoLightDto;
import com.openclassrooms.escalade.entity.*;
import com.openclassrooms.escalade.mapper.TopoMapper;
import com.openclassrooms.escalade.mapper.TopoUserMapper;
import com.openclassrooms.escalade.model.TopoSearch;
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
import java.util.stream.Collectors;

/**
 * Service to manage Topo
 *
 * @see TopoDto
 * @see TopoLightDto
 * @see TopoMapper
 * @see TopoRepository
 */
@Service
@RequiredArgsConstructor
public class TopoService {

    public final TopoRepository topoRepository;
    private final TopoMapper topoMapper;
    private final UserRepository userRepository;
    private final CotationRepository cotationRepository;
    private final SpotRepository spotRepository;
    private final FileStorageService fileStorageService;
    private final PhotoRepository photoRepository;
    private final TopoUserRepository topoUserRepository;
    private final TopoUserMapper topoUserMapper;

    /**
     * Method to create or upadte a Topo. If it's an update, we must retrieve all linked entities
     *
     * @param topoDto the topo to save
     *
     * @return TopoDto saved
     *
     * @see TopoRepository#save(Object)
     * @see EntityNotFoundException
     */
    public TopoDto createOrUpdate(TopoDto topoDto){
        User user = userRepository.findById(topoDto.getCreatorId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMin = cotationRepository.findById(topoDto.getCotationMin().getId()).orElseThrow(EntityNotFoundException::new);
        Cotation cotationMax = cotationRepository.findById(topoDto.getCotationMax().getId()).orElseThrow(EntityNotFoundException::new);
        List<Spot> spots = new ArrayList<>();
        Photo photo = null;
        if (topoDto.getPhoto() != null) {
            photo = photoRepository.findById(topoDto.getPhoto().getId()).orElseThrow(EntityNotFoundException::new);
        }
        if (topoDto.getSpots() != null && topoDto.getSpots().size() > 0) {
            topoDto.getSpots().forEach(
                    element -> spots
                            .add(this.spotRepository.findById(element.getId())
                                    .orElseThrow(EntityNotFoundException::new)));
        }
        Topo topo = Topo.builder()
                .id(topoDto.getId())
                .spots(spots)
                .cotationMin(cotationMin)
                .cotationMax(cotationMax)
                .country(topoDto.getCountry())
                .description(topoDto.getDescription())
                .name(topoDto.getName())
                .region(topoDto.getRegion())
                .topoCreator(user)
                .publicationDate(topoDto.getPublicationDate())
                .photo(photo)
                .build();
        TopoDto result = topoMapper.toTopoDto(topoRepository.save(topo));
        this.getPhotosToBase64(result);

        // If it's an update, we must retrieve TopoUsers which are linked to the topo to update
        if (topoDto.getTopoUsers() != null && topoDto.getTopoUsers().size() > 0) {
            topoUserRepository.findAllByTopoIdAndOwnerId(topo.getId(), topo.getTopoCreator().getId()).forEach(
                    (topoUser -> result.getTopoUsers().add(topoUserMapper.toTopoUserDto(topoUser)))
            );
            // Else, we create a new TopoUser which contains informations about the topo created and the user who created it
        } else {
            TopoUser topoUser = TopoUser.builder()
                    .available(true)
                    .owner(topo.getTopoCreator())
                    .topo(topo)
                    .build();
            result.getTopoUsers().add(topoUserMapper.toTopoUserDto(topoUserRepository.save(topoUser)));
        }
        return result;
    }

    /**
     * Method to retrieve all topos
     *
     * @param searchCriteria search criteria
     * @param page pagination criteria
     *
     * @return page of TopoLightDto
     *
     * @see TopoSearch
     * @see TopoPredicateBuilder
     * @see TopoRepository#findAll(Predicate, Pageable)
     */
    public Page<TopoLightDto> findAll(TopoSearch searchCriteria, Pageable page){
        Page<TopoLightDto> results = topoRepository.findAll(TopoPredicateBuilder.buildSearch(searchCriteria), page)
                .map(topoMapper::toTopoLightDto);
        results.forEach(topo -> {
            if (topo.getPhoto() != null) {
                topo.getPhoto().convertFileToBase64String(fileStorageService.load(topo.getPhoto().getName()));
            }
        });
        return results;
    }

    /**
     * Method to get a Topo by it's id. We also get the topoUsers linked
     *
     * @param id id of the requested topo
     *
     * @return TopoDto
     *
     * @see TopoUserRepository#findAllByTopoId(Long)
     * @see this#getPhotosToBase64(TopoDto)
     * @see TopoRepository#findById(Object)
     * @see EntityNotFoundException
     */
    public TopoDto findById(Long id){
        TopoDto topo = topoMapper.toTopoDto(topoRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        topo.setTopoUsers(topoUserRepository.findAllByTopoId(id).stream().map(topoUserMapper::toTopoUserDto)
                .collect(Collectors.toList()));
        this.getPhotosToBase64(topo);
        return topo;
    }

    /**
     * Method to add a photo to a Topo. If the topo already has a photo, we delete it.
     *
     * @param topoId id of the topo to which we want to add a photo
     * @param file MultipartFile which represents the photo
     *
     * @return TopoDto
     *
     * @see FileStorageService#delete(String)
     * @see FileStorageService#save(MultipartFile, String)
     * @see TopoRepository#save(Object)
     * @see EntityNotFoundException
     */
    public TopoDto addPhoto(Long topoId, MultipartFile file) {
        Topo topo = topoRepository.findById(topoId).orElseThrow(EntityNotFoundException::new);
        if (topo.getPhoto() != null) {
            this.fileStorageService.delete(topo.getPhoto().getName());
        } else {
            topo.setPhoto(new Photo());
        }
        topo.getPhoto().setExtension(Objects.requireNonNull(file.getContentType())
                .substring(file.getContentType().indexOf('/')+1));
        topo.getPhoto().setName("photo" + UUID.randomUUID() + "." + topo.getPhoto().getExtension());
        try {
            fileStorageService.save(file, topo.getPhoto().getName());
            TopoDto result = topoMapper.toTopoDto(topoRepository.save(topo));
            this.getPhotosToBase64(result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Method to delete a Topo. We must first remove the topoUsers and the photo files linked to the Topo to delete
     *
     * @param id id of the topo to delete
     *
     * @see FileStorageService#delete(String)
     * @see TopoUserRepository#findAllByTopoId(Long)
     * @see TopoRepository#delete(Object)
     * @see EntityNotFoundException
     */
    public void delete(Long id) {
        Topo topo = topoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (topo.getPhoto() != null) {
            this.fileStorageService.delete(topo.getPhoto().getName());
        }
        List<TopoUser> topoUsers = topoUserRepository.findAllByTopoId(id);
        if (topoUsers.size() > 0) {
            topoUsers.forEach(topoUserRepository::delete);
        }
        topoRepository.delete(topo);
    }

    /**
     * Method to convert photo files to base 64 in order to send them to the client
     *
     * @param topo the topo to which we want to convert the photo files
     *
     * @see PhotoDto#convertFileToBase64String(Resource)
     * @see FileStorageService#load(String)
     */
    private void getPhotosToBase64(TopoDto topo) {
        if (topo.getPhoto() != null) {
            topo.getPhoto().convertFileToBase64String(fileStorageService.load(topo.getPhoto().getName()));
        }
    }
}
