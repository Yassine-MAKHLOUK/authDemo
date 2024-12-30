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
    public List<UserDto> usersByFullName(String search) {
        if (search == null || search.trim().isEmpty()) {
            throw new IllegalArgumentException("Search parameter cannot be null or empty.");
        }

        return userRepository.findByFullNameContainingIgnoreCase(search)
                .stream()
                .map(userDtoMapper)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto newUserDetails, String email)
            throws UserNotFoundException, UserServiceLogicException {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with id " + email));

            user.setEmail(newUserDetails.email());
            user.setFullName(newUserDetails.fullName());

            userRepository.save(user);
            return newUserDetails;

        }catch(UserNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        }catch(Exception e) {
            throw new UserServiceLogicException();
        }
    }

}
