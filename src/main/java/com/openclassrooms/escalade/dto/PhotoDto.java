package com.openclassrooms.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDto {

    private Long id;
    private String name;
    private String extension;
    private MultipartFile file;
}
