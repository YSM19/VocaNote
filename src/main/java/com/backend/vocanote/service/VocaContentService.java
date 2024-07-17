package com.backend.vocanote.service;

import com.backend.vocanote.dto.VocaContentDTO;
import com.backend.vocanote.entity.VocaContent;

import java.util.List;

public interface VocaContentService {

    public List<VocaContent> findAllVocaContent();
    public VocaContent findVocaContentById(Long id);

    public VocaContent createVocaContent(VocaContentDTO vocaContentDTO);

    public VocaContent updateVocaContent(Long id, VocaContentDTO vocaContentDTO);

    public void deleteVocaContentById(Long id);

}
