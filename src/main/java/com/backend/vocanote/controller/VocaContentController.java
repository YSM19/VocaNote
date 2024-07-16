package com.backend.vocanote.controller;

import com.backend.vocanote.dto.VocaContentDTO;
import com.backend.vocanote.entity.VocaContent;
import com.backend.vocanote.service.VocaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class VocaContentController {

    @Autowired
    private VocaContentService vocaContentService;

    // read
    // findById
    @GetMapping
    public ResponseEntity<VocaContent> findQuizById(@PathVariable Long id) {
        VocaContent vocaContent = vocaContentService.findQuizById(id);
        if (vocaContent != null) {
            return ResponseEntity.ok(vocaContent);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // create
    @PostMapping
    public ResponseEntity<VocaContent> createQuiz(@RequestBody VocaContentDTO vocaContentDTO) {

    }


}
