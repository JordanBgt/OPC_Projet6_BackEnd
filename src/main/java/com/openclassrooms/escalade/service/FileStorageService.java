package com.openclassrooms.escalade.service;

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

/**
 * Service to manage document storage
 */
@Service
public class FileStorageService {

    private final Path root = Paths.get("uploads");

    /**
     * Method to save a file
     *
     * @param file MultipartFile to save
     * @param fileName name of the file to save
     *
     * @see MultipartFile
     * @see Files
     */
    public void save(MultipartFile file, String fileName) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error : " + e.getMessage());
        }

    }

    /**
     * Method to load a file
     *
     * @param fileName name of the file to load
     *
     * @return Resource which represents the file loaded
     *
     * @see Resource
     */
    public Resource load(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file !");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    /**
     * Method to delete a file
     *
     * @param fileName name of the file to delete
     *
     * @see FileSystemUtils#deleteRecursively(Path)
     */
    public void delete(String fileName) {
        Path file = root.resolve(fileName);
        try {
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException e) {
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }
}
