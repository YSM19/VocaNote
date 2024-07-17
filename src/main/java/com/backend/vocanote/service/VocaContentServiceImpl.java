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
    public List<VocaContent> findAllVocaContent() {
        return vocaContentRepository.findAll();
    }
    // findById
    public VocaContent findVocaContentById(Long id) {
        Optional<VocaContent> vocaContent = vocaContentRepository.findById(id);
        return vocaContent.orElseThrow(() -> new RuntimeException("VocaContent is not found"));
    }

    // create
    public VocaContent createVocaContent(VocaContentDTO vocaContentDTO) {

        VocaContent vocaContent = new VocaContent();
        vocaContent.setWord(vocaContentDTO.getWord());
        vocaContent.setKoreanWord(vocaContentDTO.getKoreanWord());

        return vocaContentRepository.save(vocaContent);
    }

    // update
    public VocaContent updateVocaContent(Long id, VocaContentDTO vocaContentDTO) {
        VocaContent vocaContent = findVocaContentById(id);
        vocaContent.setWord(vocaContentDTO.getWord());
        vocaContent.setKoreanWord(vocaContentDTO.getKoreanWord());

        return vocaContentRepository.save(vocaContent);
    }

    // delete
    public void deleteVocaContentById(Long id) {
        VocaContent vocaContent = findVocaContentById(id);
        vocaContentRepository.delete(vocaContent);

    }

}
