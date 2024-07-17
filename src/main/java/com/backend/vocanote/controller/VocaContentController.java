package com.backend.vocanote.controller;

import com.backend.vocanote.dto.VocaContentDTO;
import com.backend.vocanote.entity.VocaContent;
import com.backend.vocanote.service.VocaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vocacontent")
public class VocaContentController {

    @Autowired
    private VocaContentService vocaContentService;

    // read
    // findById
    @GetMapping("/{id}")
    public ResponseEntity<VocaContent> findVocaContentById(@PathVariable Long id) {
        VocaContent vocaContent = vocaContentService.findVocaContentById(id);
        if (vocaContent != null) {
            return ResponseEntity.ok(vocaContent);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // create
    @PostMapping
    public ResponseEntity<VocaContent> createVocaContent(@RequestBody VocaContentDTO vocaContentDTO) {
        VocaContent vocaContent = vocaContentService.createVocaContent(vocaContentDTO);
        if (vocaContent != null) {
            return ResponseEntity.ok(vocaContent);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<VocaContent> updateVocaContent(@PathVariable Long id, @RequestBody VocaContentDTO vocaContentDTO) {
        VocaContent vocaContent = vocaContentService.updateVocaContent(id, vocaContentDTO);
        if (vocaContent != null) {
            return ResponseEntity.ok(vocaContent);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVocaContent(@PathVariable Long id) {
        vocaContentService.deleteVocaContentById(id);

        return ResponseEntity.noContent().build();
    }

}
