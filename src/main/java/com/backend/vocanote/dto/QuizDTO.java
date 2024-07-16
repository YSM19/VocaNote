package com.backend.vocanote.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class QuizDTO {

    @NotEmpty(message = "ID is required")
    private Long id;

    @NotEmpty(message = "Word is required")
    private String word;

    @NotEmpty(message = "Kword is required")
    private String koreanWord;
}
