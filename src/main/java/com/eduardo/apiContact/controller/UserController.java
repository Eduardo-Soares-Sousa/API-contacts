package com.eduardo.apiContact.controller;

import com.eduardo.apiContact.business.service.UserService;
import com.eduardo.apiContact.model.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.saveUser(userDto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getLoggedUSer() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteLoggedUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
