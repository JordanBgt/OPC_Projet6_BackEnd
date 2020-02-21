package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.entities.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UtilisateurController {

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Utilisateur> getUser() {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<Utilisateur>(user, HttpStatus.OK);
    }
}
