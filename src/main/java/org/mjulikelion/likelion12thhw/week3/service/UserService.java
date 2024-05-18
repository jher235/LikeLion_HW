package org.mjulikelion.likelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.authentication.PasswordHashEncryption;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.LoginUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.ModifyUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.RegisterUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.Organization.GetOrganizationsResponseData;
import org.mjulikelion.likelion12thhw.week3.dto.response.Organization.OrganizationResponse;
import org.mjulikelion.likelion12thhw.week3.exception.ConflictException;
import org.mjulikelion.likelion12thhw.week3.exception.ForbiddenException;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.repository.UserOrganizationRepository;
import org.mjulikelion.likelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserOrganizationRepository userOrganizationRepository;
    private final PasswordHashEncryption passwordHashEncryption;

    public void register(RegisterUserDto registerUserDto) {
        if (userRepository.existsByEmail(registerUserDto.getEmail())) {
            throw new ConflictException(ErrorCode.USER_CONFLICT);
        }

        String encryptPassword = passwordHashEncryption.encrypt(registerUserDto.getPassword());

        log.info(encryptPassword);
        User user = User.builder()
                .name(registerUserDto.getName())
                .email(registerUserDto.getEmail())
                .password(encryptPassword)
                .build();

        userRepository.save(user);
        log.info("userId: " + user.getId());
    }

    public void modify(ModifyUserDto modifyUserDto, User user) {
        user.modify(modifyUserDto.getEmail(), modifyUserDto.getName(), passwordHashEncryption.encrypt(modifyUserDto.getPassword()));
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public UUID login(LoginUserDto loginUserDto) {
        User user = this.findByEmail(loginUserDto.getEmail());
        
        if (!passwordHashEncryption.matches(loginUserDto.getPassword(), user.getPassword())) {
            throw new ForbiddenException(ErrorCode.USER_UNAUTHORIZED);
        }
        return user.getId();
    }

    public GetOrganizationsResponseData getOrganizations(User user) {
        return new GetOrganizationsResponseData(userOrganizationRepository.findAllByUser(user).stream()
                .map(i -> OrganizationResponse.organizationResponse(i.getOrganization()))
                .collect(Collectors.toList()));
    }


    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

}
