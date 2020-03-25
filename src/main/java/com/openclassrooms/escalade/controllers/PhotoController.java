package com.openclassrooms.escalade.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoController {
}
