package com.backend.vocanote.dto;

import com.backend.vocanote.entity.VocaList;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VocaListDTO {

    @NotEmpty(message = "ID is required")
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Name can have up to 100 characters")
    private String name;

    @Size(max = 1000, message = "Description can have up to 1000 characters")
    private String description;

//    private MultipartFile image;
    private String imagePath;

    public VocaList toEntity() {
        return VocaList.builder()
                .id(id)
                .name(name)
                .description(description)
                .imagePath(imagePath)
                .build();
    }

    public static VocaListDTO toDTO(VocaList vocaList) {
        return VocaListDTO.builder()
                .id(vocaList.getId())
                .name(vocaList.getName())
                .description(vocaList.getDescription())
                .imagePath(vocaList.getImagePath())
                .build();
    }
}
