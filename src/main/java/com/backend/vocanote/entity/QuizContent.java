package com.backend.vocanote.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "quizcontent")
public class QuizContent { // 퀴즈문제

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String imagePath;

}
