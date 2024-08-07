package com.backend.vocanote.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "quiz")
public class Quiz { // 퀴즈 하나하나
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String koreanWord;
}
