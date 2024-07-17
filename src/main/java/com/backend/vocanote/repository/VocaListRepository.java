package com.backend.vocanote.repository;

import com.backend.vocanote.entity.VocaList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocaListRepository extends JpaRepository<VocaList, Long> {
}