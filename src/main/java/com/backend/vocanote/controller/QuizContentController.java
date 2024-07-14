package com.backend.vocanote.controller;

import com.backend.vocanote.dto.QuizContentDTO;
import com.backend.vocanote.entity.QuizContent;
import com.backend.vocanote.service.QuizContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/quizcontent")
public class QuizContentController {

    @Autowired
    private QuizContentService quizContentService;

    @PostMapping
    public ResponseEntity<QuizContent> createQuizContent(@ModelAttribute QuizContentDTO quizContentDTO) {
        try {
            QuizContent createdQuizContent = quizContentService.createQuizContent(quizContentDTO);
            return ResponseEntity.ok(createdQuizContent); // HTTP 200 OK와 함께 createdQuiz 객체를 반환
        } catch (IOException e) {
            return ResponseEntity.badRequest().build(); // HTTP 400 Bad Request 반환
        } catch (Exception e) {
            // 기타 예외 처리
            System.err.println("An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // HTTP 500 Internal Server Error 반환
        }
    }
}
