package com.eduardo.apiContact.business.converter;

import com.eduardo.apiContact.model.dto.UserDto;
import com.eduardo.apiContact.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User converterToUserEntity(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .build();
    }

    public UserDto converterToUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
