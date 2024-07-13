package com.backend.vocanote.service;

import com.backend.vocanote.dto.QuizDTO;

public interface QuizService {

    public QuizDTO readQuiz(Long id);

    public boolean createQuiz(QuizDTO quizDTO);

    public boolean updateQuiz(QuizDTO quizDTO);

    public boolean deleteQuiz(Long id);

}
