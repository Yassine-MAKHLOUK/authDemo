package com.example.authDemo.controllers;

import com.example.authDemo.dtos.UserDto;
import com.example.authDemo.entities.User;
import com.example.authDemo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> allUsers() {
        List <UserDto> users = userService.usersList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> allUsersByFullName(@RequestParam String user) {
        List <UserDto> users = userService.usersByFullName(user);
        return ResponseEntity.ok(users);
    }
}
