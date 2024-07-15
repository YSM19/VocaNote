package com.backend.vocanote.controller;

import com.backend.vocanote.dto.UserDTO;
import com.backend.vocanote.entity.User;
import com.backend.vocanote.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Read
    // findAll
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        log.info("Find all users - Getmapping");
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    // findById
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        log.info("Find user by id - Getmapping");
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // create
    @PostMapping
    public ResponseEntity<String> createUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("Create new user - PostMapping");
        boolean iscreated = userService.createUser(userDTO);
        if (iscreated) {
            return ResponseEntity.ok("User with ID " + id + " updated with name " + userDTO.getName());
        }
        // userDTO 객체를 사용하여 사용자 생성 로직 수행
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User created with name: " + id + "not found");
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("Update user - PutMapping");
        boolean isUpdated = userService.updateUser(id, userDTO);
        if (isUpdated) {
            return ResponseEntity.ok("User with ID " + id + " updated with name " + userDTO.getName());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with ID " + id + " updated with name " + userDTO.getName());
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Delete user - DeleteMapping");
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
