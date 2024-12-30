package com.example.authDemo.controllers;


import com.example.authDemo.dtos.UserDto;
import com.example.authDemo.entities.User;
import com.example.authDemo.exceptions.UserNotFoundException;
import com.example.authDemo.exceptions.UserServiceLogicException;
import com.example.authDemo.services.AdminService;
import com.example.authDemo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/admin")
@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateMe(@RequestBody UserDto newUserDetails, @RequestParam String email) throws UserNotFoundException, UserServiceLogicException {
        UserDto newUser = adminService.updateUser(newUserDetails, email);
        return ResponseEntity.ok(newUser);
    }
}
