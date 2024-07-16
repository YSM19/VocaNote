package com.backend.vocanote.repository;

import com.backend.vocanote.entity.VocaContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocaContentRepository extends JpaRepository<VocaContent, Long> {
}
