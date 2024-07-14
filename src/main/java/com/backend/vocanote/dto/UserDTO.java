package com.backend.vocanote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본 생성자를 자동으로 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자를 자동으로 생성
public class UserDTO {
    private Long id;
    private String name;
    private String email;

}
