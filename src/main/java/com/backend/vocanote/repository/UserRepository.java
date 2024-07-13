package com.backend.vocanote.repository;

import com.backend.vocanote.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
