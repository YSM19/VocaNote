package com.backend.vocanote.service;

import com.backend.vocanote.dto.QuizDTO;
import com.backend.vocanote.entity.Quiz;
import com.backend.vocanote.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;

    // read
    // findAll
    public List<Quiz> findAllQuiz() {
        return quizRepository.findAll();
    }
    // findById
    public Quiz findQuizById(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    // create
    public Quiz createQuiz(QuizDTO quizDTO) {

        Quiz quiz = new Quiz();
        quiz.setWord(quizDTO.getWord());
        quiz.setKoreanWord(quizDTO.getKoreanWord());

        return quizRepository.save(quiz);
    }

    // update
    public Quiz updateQuiz(Long id, QuizDTO quizDTO) {
        Quiz quiz = findQuizById(id);
        quiz.setWord(quizDTO.getWord());
        quiz.setKoreanWord(quizDTO.getKoreanWord());

        return quizRepository.save(quiz);
    }

    // delete
    public void deleteQuizById(Long id) {
        Quiz quiz = findQuizById(id);
        quizRepository.delete(quiz);

    }

}
