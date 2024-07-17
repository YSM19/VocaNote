package com.backend.vocanote.service;

import com.backend.vocanote.dto.VocaListDTO;
import com.backend.vocanote.entity.VocaList;
import com.backend.vocanote.repository.VocaListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class VocaListService {

    @Autowired
    private VocaListRepository vocaListRepository;

    private final Path root = Paths.get("uploads");

    // Create
    public VocaList createVocaList(VocaListDTO vocaListDTO) throws IOException {

        VocaList vocaList = new VocaList();
        vocaList.setName(vocaListDTO.getName());
        vocaList.setDescription(vocaListDTO.getDescription());
//        // 이미지 경로를 저장 - 상대 경로
//        String filename = saveImage(vocaListDTO.getImagePath());
//        vocaList.setImagePath("/uploads/" + filename);

        return vocaListRepository.save(vocaList);
    }

    // Read
    // findAll
    public List<VocaList> findAllVocaList() {
        return vocaListRepository.findAll();
    }
    // findById
    public VocaList findVocaListById(Long id) {
        Optional<VocaList> vocaList = vocaListRepository.findById(id);
        return vocaList.orElseThrow(() -> new RuntimeException("VocaList not found"));
    }

    // Update
    public VocaList updateVocaList(Long id, VocaListDTO vocaListDTO) throws IOException {

        VocaList vocaList = findVocaListById(id);

        vocaList.setName(vocaListDTO.getName());
        vocaList.setDescription(vocaListDTO.getDescription());

//        // 이미지
//        if (vocaListDTO.getImagePath() != null && !vocaListDTO.getImagePath().isEmpty()) {
//            String filename = saveImage(vocaListDTO.getImagePath());
//            vocaList.setImagePath("/uploads/" + filename);
//        }
        return vocaListRepository.save(vocaList);
    }

    // Delete
    public void deleteVocaListById(Long id) {
        VocaList vocaList = findVocaListById(id);
        vocaListRepository.delete(vocaList);
    }

//    private String saveImage(String file) throws IOException {
//        if (!Files.exists(root)) {
//            Files.createDirectory(root);
//        }
//
//        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//        Files.copy(file.getInputStream(), this.root.resolve(filename));
//
//        return filename;
//    }

}
