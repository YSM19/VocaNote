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

    // Update
    public QuizContent getQuizContentById(Long id) {
        Optional<QuizContent> quizContent = quizContentRepository.findById(id);
        return quizContent.orElseThrow(() -> new RuntimeException("QuizContent not found"));
    }

    // Delete
    public void deleteQuizContentById(Long id) {
        QuizContent quizContent = getQuizContentById(id);
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
