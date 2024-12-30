package com.example.authDemo.services;

import com.example.authDemo.dtos.UserDto;
import com.example.authDemo.dtos.UserDtoMapper;
import com.example.authDemo.entities.User;
import com.example.authDemo.exceptions.UserNotFoundException;
import com.example.authDemo.exceptions.UserServiceLogicException;
import com.example.authDemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public AdminService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public UserDto updateUser(UserDto newUserDetails, String email)
            throws UserNotFoundException, UserServiceLogicException {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with id " + email));

            user.setEmail(newUserDetails.email());
            user.setFullName(newUserDetails.fullName());
            user.setRole(newUserDetails.role());

            userRepository.save(user);
            return userDtoMapper.apply(userRepository.findByEmail(user.getEmail()).get());

        }catch(UserNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        }catch(Exception e) {
            throw new UserServiceLogicException();
        }
    }
}
