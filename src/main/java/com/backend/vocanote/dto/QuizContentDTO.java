package com.backend.vocanote.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuizContentDTO {

    private String name;
    private String description;
    private MultipartFile image;
}
