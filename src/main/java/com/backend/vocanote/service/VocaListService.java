package com.backend.vocanote.service;

import com.backend.vocanote.dto.VocaListDTO;
import com.backend.vocanote.entity.VocaList;
import com.backend.vocanote.repository.QuizContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VocaListService {

    @Autowired
    private QuizContentRepository quizContentRepository;

    private final Path root = Paths.get("uploads");

    // Create
    public VocaList createQuizContent(VocaListDTO vocaListDTO) throws IOException {
        String filename = saveImage(vocaListDTO.getImage());

        VocaList vocaList = new VocaList();
        vocaList.setName(vocaListDTO.getName());
        vocaList.setDescription(vocaListDTO.getDescription());
        // 이미지 경로를 저장 - 상대 경로
        vocaList.setImagePath("/uploads/" + filename);

        return quizContentRepository.save(vocaList);
    }

    // Read
    // findAll
    public List<VocaList> findAllQuizContent() {
        return quizContentRepository.findAll();
    }
    // findById
    public VocaList findQuizContentById(Long id) {
        Optional<VocaList> quizContent = quizContentRepository.findById(id);
        return quizContent.orElseThrow(() -> new RuntimeException("QuizContent not found"));
    }

    // Update
    public VocaList updateQuizContent(Long id, VocaListDTO vocaListDTO) throws IOException {
        VocaList vocaList = findQuizContentById(id);

        vocaList.setName(vocaListDTO.getName());
        vocaList.setDescription(vocaListDTO.getDescription());

        // 이미지
        if (vocaListDTO.getImage() != null && !vocaListDTO.getImage().isEmpty()) {
            String filename = saveImage(vocaListDTO.getImage());
            vocaList.setImagePath("/uploads/" + filename);
        }
        return quizContentRepository.save(vocaList);
    }

    // Delete
    public void deleteQuizContentById(Long id) {
        VocaList vocaList = findQuizContentById(id);
        quizContentRepository.delete(vocaList);
    }

    private String saveImage(MultipartFile file) throws IOException {
        if (!Files.exists(root)) {
            Files.createDirectory(root);
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), this.root.resolve(filename));

        return filename;
    }

}
