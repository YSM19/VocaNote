package com.backend.vocanote.controller;

import com.backend.vocanote.dto.QuizDTO;
import com.backend.vocanote.entity.Quiz;
import com.backend.vocanote.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // read
    // findById
    @GetMapping
    public ResponseEntity<Quiz> findQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.findQuizById(id);
        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // create
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO) {

    }


}
