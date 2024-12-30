package com.example.authDemo.dtos;


import com.example.authDemo.enums.UserRole;

public record UserDto(
        Long id,
        String fullName,
        String email,
        UserRole role
        ){
}
