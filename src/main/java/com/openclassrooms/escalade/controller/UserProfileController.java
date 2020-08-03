package com.openclassrooms.escalade.controller;

import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.dto.UserProfileDto;
import com.openclassrooms.escalade.service.TopoUserService;
import com.openclassrooms.escalade.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * Controler to handler UserProfile. UserProfile contains informations about the user, the topos he created/owns/rents,
 * and the spots that he created.
 *
 * @see UserProfileDto
 * @see UserProfileService
 * @see TopoUserService
 */
@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final TopoUserService topoUserService;

    /**
     * Method to get a UserProfile
     * URL : /api/profile
     * Only an admin or a connected user can request his profile
     *
     * @param userId user's id
     *
     * @return UserProfilDto
     *
     * @see UserProfileService#findUserProfile(Long)
     */
    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public UserProfileDto getProfile(@RequestParam Long userId) {
        log.info("Start of user profile recovery");
        return userProfileService.findUserProfile(userId);
    }

    /**
     * Method to update the booking state
     * URL : /api/profile/bookings
     * Only an admin or a connected user can update the booking state
     *
     * @param topoUserDto TopoUserDto updated with the informations of the user who wants to book the topo
     *
     * @return TopoUserDto updated
     *
     * @see TopoUserService#updateTopoUser(TopoUserDto)
     */
    @PostMapping("/bookings")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public TopoUserDto updateBooking(@RequestBody TopoUserDto topoUserDto) {
        log.info("Start updating the reservation");
        return topoUserService.updateTopoUser(topoUserDto);
    }

    /**
     * Method to add a topo to the topos that the user owned
     * URL : /api/profile/topos
     * Only an admin or a connected user can add a topo to the topos he owned
     *
     * @param topoUserDto TopoUserDto which contains information about the topo and the owner
     *
     * @return TopoUserDto created
     *
     * @see TopoUserService#createTopoUser(TopoUserDto)
     */
    @PostMapping("/topos")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public TopoUserDto addTopoUser(@RequestBody TopoUserDto topoUserDto) {
        log.info("Start creating topoUser");
        return topoUserService.createTopoUser(topoUserDto);
    }
}
