package com.backend.vocanote.service;

import com.backend.vocanote.dto.QuizContentDTO;
import com.backend.vocanote.entity.QuizContent;
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
public class QuizContentService {

    @Autowired
    private QuizContentRepository quizContentRepository;

    private final Path root = Paths.get("uploads");

    // Create
    public QuizContent createQuizContent(QuizContentDTO quizContentDTO) throws IOException {
        String filename = saveImage(quizContentDTO.getImage());

        QuizContent quizContent = new QuizContent();
        quizContent.setName(quizContentDTO.getName());
        quizContent.setDescription(quizContentDTO.getDescription());
        // 이미지 경로를 저장 - 상대 경로
        quizContent.setImagePath("/uploads/" + filename);

        return quizContentRepository.save(quizContent);
    }

    // Read
    // findAll
    public List<QuizContent> findAllQuizContent() {
        return quizContentRepository.findAll();
    }
    // findById
    public QuizContent findQuizContentById(Long id) {
        Optional<QuizContent> quizContent = quizContentRepository.findById(id);
        return quizContent.orElseThrow(() -> new RuntimeException("QuizContent not found"));
    }

    // Update
    public QuizContent updateQuizContent(Long id, QuizContentDTO quizContentDTO) throws IOException {
        QuizContent quizContent = findQuizContentById(id);

        quizContent.setName(quizContentDTO.getName());
        quizContent.setDescription(quizContentDTO.getDescription());

        // 이미지
        if (quizContentDTO.getImage() != null && !quizContentDTO.getImage().isEmpty()) {
            String filename = saveImage(quizContentDTO.getImage());
            quizContent.setImagePath("/uploads/" + filename);
        }
        return quizContentRepository.save(quizContent);
    }

    // Delete
    public void deleteQuizContentById(Long id) {
        QuizContent quizContent = findQuizContentById(id);
        quizContentRepository.delete(quizContent);
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
