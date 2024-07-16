package com.backend.vocanote.service;

import com.backend.vocanote.dto.VocaContentDTO;
import com.backend.vocanote.entity.VocaContent;
import com.backend.vocanote.repository.VocaContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VocaContentServiceImpl implements VocaContentService {

    @Autowired
    private VocaContentRepository vocaContentRepository;

    // read
    // findAll
    public List<VocaContent> findAllQuiz() {
        return vocaContentRepository.findAll();
    }
    // findById
    public VocaContent findQuizById(Long id) {
        Optional<VocaContent> quiz = vocaContentRepository.findById(id);
        return quiz.orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    // create
    public VocaContent createQuiz(VocaContentDTO vocaContentDTO) {

        VocaContent vocaContent = new VocaContent();
        vocaContent.setWord(vocaContentDTO.getWord());
        vocaContent.setKoreanWord(vocaContentDTO.getKoreanWord());

        return vocaContentRepository.save(vocaContent);
    }

    // update
    public VocaContent updateQuiz(Long id, VocaContentDTO vocaContentDTO) {
        VocaContent vocaContent = findQuizById(id);
        vocaContent.setWord(vocaContentDTO.getWord());
        vocaContent.setKoreanWord(vocaContentDTO.getKoreanWord());

        return vocaContentRepository.save(vocaContent);
    }

    // delete
    public void deleteQuizById(Long id) {
        VocaContent vocaContent = findQuizById(id);
        vocaContentRepository.delete(vocaContent);

    }

}
