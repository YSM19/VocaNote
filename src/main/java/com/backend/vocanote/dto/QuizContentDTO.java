package com.backend.vocanote.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuizContentDTO {

    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Name can have up to 100 characters")
    private String name;

    @NotEmpty(message = "Name is required")
    @Size(max = 1000, message = "Description can have up to 1000 characters")
    private String description;

    private MultipartFile image;
}
