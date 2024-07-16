package com.backend.vocanote.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VocaContentDTO {

    @NotEmpty(message = "ID is required")
    private Long id;

    @NotEmpty(message = "Word is required")
    private String word;

    @NotEmpty(message = "Kword is required")
    private String koreanWord;
}
