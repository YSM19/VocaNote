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
import java.util.UUID;

@Service
public class QuizContentService {

    @Autowired
    private QuizContentRepository quizContentRepository;

    private final Path root = Paths.get("uploads");

    public QuizContent createQuizContent(QuizContentDTO quizContentDTO) throws IOException {
        String filename = saveImage(quizContentDTO.getImage());

        QuizContent quizContent = new QuizContent();
        quizContent.setName(quizContentDTO.getName());
        quizContent.setDescription(quizContentDTO.getDescription());
        quizContent.setImagePath("/uploads/" + filename);

        return quizContentRepository.save(quizContent);
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
