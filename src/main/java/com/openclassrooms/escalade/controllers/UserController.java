package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.UserDto;
import com.openclassrooms.escalade.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public UserDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

}
