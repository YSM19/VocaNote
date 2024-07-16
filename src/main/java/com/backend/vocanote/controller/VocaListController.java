package com.backend.vocanote.controller;

import com.backend.vocanote.dto.VocaListDTO;
import com.backend.vocanote.entity.VocaList;
import com.backend.vocanote.service.VocaListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/quizcontent")
public class VocaListController {

    @Autowired
    private VocaListService vocaListService;

    // Create
    @PostMapping
    public ResponseEntity<VocaList> createQuizContent(@ModelAttribute VocaListDTO vocaListDTO) {
        try {
            VocaList createdVocaList = vocaListService.createQuizContent(vocaListDTO);
            return ResponseEntity.ok(createdVocaList); // HTTP 200 OK와 함께 createdQuiz 객체를 반환
        } catch (IOException e) {
            return ResponseEntity.badRequest().build(); // HTTP 400 Bad Request 반환
        } catch (Exception e) {
            // 기타 예외 처리
            System.err.println("An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // HTTP 500 Internal Server Error 반환
        }
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<VocaList> getQuizContent(@PathVariable Long id) {
        VocaList vocaList = vocaListService.findQuizContentById(id);
        return ResponseEntity.ok(vocaList);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<VocaList> deleteQuizContent(@PathVariable Long id) {
        vocaListService.deleteQuizContentById(id);
        return ResponseEntity.noContent().build();
    }

}
