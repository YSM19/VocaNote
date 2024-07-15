package com.backend.vocanote.dto;

import com.backend.vocanote.entity.QuizContent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizContentDTO {

    @NotEmpty(message = "ID is required")
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Name can have up to 100 characters")
    private String name;

    @Size(max = 1000, message = "Description can have up to 1000 characters")
    private String description;

//    private MultipartFile image;
    private String imagePath;

    public QuizContent toEntity() {
        return QuizContent.builder()
                .id(id)
                .name(name)
                .description(description)
                .imagePath(imagePath)
                .build();
    }

    public static QuizContentDTO toDTO(QuizContent quizContent) {
        return QuizContentDTO.builder()
                .id(quizContent.getId())
                .name(quizContent.getName())
                .description(quizContent.getDescription())
                .imagePath(quizContent.getImagePath())
                .build();
    }
}
