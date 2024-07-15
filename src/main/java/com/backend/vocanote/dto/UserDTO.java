package com.backend.vocanote.dto;

import com.backend.vocanote.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor // 기본 생성자를 자동으로 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자를 자동으로 생성
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;

    public User toEntity() { // DTO 객체를 --> Entity 객체로 변환
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
    }

    public static UserDTO toDTO(User user) { // Entity 객체를 --> DTO 객체로 변환
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
