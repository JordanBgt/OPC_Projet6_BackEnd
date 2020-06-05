package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Base64;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class PhotoDto {

    private Long id;
    private String name;
    private String extension;
    private String fileToBase64String;

    public void convertFileToBase64String(Resource file) {
        try {
            byte[] photoToByteArray = IOUtils.toByteArray(file.getInputStream());
            this.fileToBase64String = Base64.getEncoder().encodeToString(photoToByteArray);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
