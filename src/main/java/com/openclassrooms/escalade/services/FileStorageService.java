package com.openclassrooms.escalade.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class FileStorageService {

    private final Path root = Paths.get("uploads");

    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload");
        }
    }

    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error : " + e.getMessage());
        }

    }

    public Resource load(String fileName) {
        try {
            Path file = root.resolve(fileName);
            log.info("PATH FILE : " + file);
            Resource resource = new UrlResource(file.toUri());
            log.info("RESOURCE : " + resource);

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file !");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public void delete(String fileName) {
        Path file = root.resolve(fileName);
        try {
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException e) {
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }
}
