package com.backend.vocanote.service;

import com.backend.vocanote.dto.VocaContentDTO;
import com.backend.vocanote.entity.VocaContent;

import java.util.List;

public interface VocaContentService {

    public List<VocaContent> findAllQuiz();
    public VocaContent findQuizById(Long id);

    public VocaContent createQuiz(VocaContentDTO vocaContentDTO);

    public VocaContent updateQuiz(Long id, VocaContentDTO vocaContentDTO);

    public void deleteQuizById(Long id);

}
