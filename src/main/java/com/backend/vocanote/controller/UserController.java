package com.backend.vocanote.controller;

import com.backend.vocanote.dto.UserDTO;
import com.backend.vocanote.entity.User;
import com.backend.vocanote.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers(@PathVariable Long id) {
        log.info("Find all users - Getmapping");
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        log.info("Find user by id - Getmapping");
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public String createUser(@RequestBody UserDTO userDTO) {
        log.info("Create new user - PostMapping");
        userService.createUser(userDTO);
        // userDTO 객체를 사용하여 사용자 생성 로직 수행
        return "User created with name: " + userDTO.getName();
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("Update user - PutMapping");
        userService.updateUser(id, userDTO);
        // id와 userDTO 객체를 사용하여 사용자 업데이트 로직 수행
        return "User with ID " + id + " updated with name " + userDTO.getName();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        log.info("Delete user - DeleteMapping");
    }
}
