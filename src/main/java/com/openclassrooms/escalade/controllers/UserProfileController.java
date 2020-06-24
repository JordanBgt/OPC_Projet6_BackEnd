package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.TopoUserDto;
import com.openclassrooms.escalade.dto.UserProfileDto;
import com.openclassrooms.escalade.services.TopoUserService;
import com.openclassrooms.escalade.services.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final TopoUserService topoUserService;

    @GetMapping
    @ResponseBody
    public UserProfileDto getProfile(@RequestParam Long userId) {
        log.info("Démarrage récupération du profile utilisateur");
        return userProfileService.findUserProfile(userId);
    }

    @PostMapping("/bookings")
    @ResponseBody
    public TopoUserDto updateBooking(@RequestBody TopoUserDto topoUserDto) {
        log.info("Démarrage mise à jour de la réservation");
        return topoUserService.updateTopoUser(topoUserDto);
    }
}
