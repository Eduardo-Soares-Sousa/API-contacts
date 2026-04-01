package com.eduardo.apiContact.business.service;

import com.eduardo.apiContact.business.converter.UserConverter;
import com.eduardo.apiContact.business.exceptions.ConflitException;
import com.eduardo.apiContact.business.exceptions.ResourceNotFoundException;
import com.eduardo.apiContact.model.dto.UserDto;
import com.eduardo.apiContact.model.entity.User;
import com.eduardo.apiContact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    public UserDto saveUser(UserDto userDto) {
        emailExist(userDto.getEmail());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userConverter.converterToUserEntity(userDto);

        return userConverter.converterToUserDto(userRepository.save(user));
    }

    public UserDto findByEmail(String email) {
        return userConverter.converterToUserDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email address not found"))
        );

    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    private void emailExist(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new ConflitException("The email " + email + " already registered");
        }
    }
}
