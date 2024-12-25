package com.example.authDemo.dtos;

import com.example.authDemo.entities.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getFullName(),
                user.getEmail()

        );
    }


}
