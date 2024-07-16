package com.backend.vocanote.service;

import com.backend.vocanote.dto.QuizDTO;
import com.backend.vocanote.entity.Quiz;

import java.util.List;

public interface QuizService {

    public List<Quiz> findAllQuiz();
    public Quiz findQuizById(Long id);

    public Quiz createQuiz(QuizDTO quizDTO);

    public Quiz updateQuiz(Long id, QuizDTO quizDTO);

    public void deleteQuizById(Long id);

}
