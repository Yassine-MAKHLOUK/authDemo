package com.example.authDemo.services;

import com.example.authDemo.dtos.UserDto;
import com.example.authDemo.dtos.UserDtoMapper;
import com.example.authDemo.entities.User;
import com.example.authDemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    public List<UserDto> usersList() {
        return userRepository.findAll()
                .stream()
                .map(userDtoMapper)
                .collect(Collectors.toList());
    }

}
