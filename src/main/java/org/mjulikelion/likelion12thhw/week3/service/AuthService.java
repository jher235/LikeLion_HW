package org.mjulikelion.likelion12thhw.week3.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.authentication.PasswordHashEncryption;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.LoginUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.RegisterUserDto;
import org.mjulikelion.likelion12thhw.week3.exception.AuthorizeException;
import org.mjulikelion.likelion12thhw.week3.exception.ConflictException;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordHashEncryption passwordHashEncryption;

    public void register(RegisterUserDto registerUserDto) {
        if (userRepository.existsByEmail(registerUserDto.getEmail())) {
            throw new ConflictException(ErrorCode.USER_CONFLICT);
        }

        String encryptPassword = passwordHashEncryption.encrypt(registerUserDto.getPassword());

        User user = User.builder()
                .name(registerUserDto.getName())
                .email(registerUserDto.getEmail())
                .password(encryptPassword)
                .build();

        userRepository.save(user);
    }

    public UUID login(LoginUserDto loginUserDto) {
        
        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(() -> new NotFoundException(ErrorCode.USER_UNAUTHORIZED));

        if (!passwordHashEncryption.matches(loginUserDto.getPassword(), user.getPassword())) {
            throw new AuthorizeException(ErrorCode.USER_UNAUTHORIZED);
        }
        return user.getId();
    }


}
