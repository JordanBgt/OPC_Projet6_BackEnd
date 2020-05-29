package com.openclassrooms.escalade.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    void init();
    void save(MultipartFile file);
    Resource load(String fileName);
}
