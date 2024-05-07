package org.mjulikelion.likelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.LoginUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.ModifyUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.RegisterUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.user.GetOrganizationsDto;
import org.mjulikelion.likelion12thhw.week3.exception.ConflictException;
import org.mjulikelion.likelion12thhw.week3.exception.ForbiddenException;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
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

    public void registerUser(RegisterUserDto registerUserDto) {
        UUID userId = UUID.randomUUID();
        User user = User.builder()
                .name(registerUserDto.getName())
                .email(registerUserDto.getEmail())
                .password(registerUserDto.getPassword())
                .build();

        if (userRepository.existsById(userId)) {
            throw new ConflictException(userId + "는 이미 존재하는 아이디입니다.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ConflictException(user.getEmail() + "는 이미 가입한 이메일입니다.");
        }
        userRepository.save(user);
        log.info("userId: " + userId);
    }

    public void modify(ModifyUserDto modifyUserDto, UUID userId) {
        User user = this.findById(userId);
        user.setName(modifyUserDto.getName());
        user.setEmail(modifyUserDto.getEmail());
        user.setPassword(modifyUserDto.getPassword());
        userRepository.save(user);
    }

    public void delete(UUID userId) {
        User user = this.findById(userId);
        userRepository.delete(user);
    }

    public UUID login(LoginUserDto loginUserDto) {
        User user = this.findByEmail(loginUserDto.getEmail());
        if (!user.getPassword().equals(loginUserDto.getPassword())) {
            throw new ForbiddenException("비밀번호가 일치하지 않습니다.");
        }
        return user.getId();
    }

    public GetOrganizationsDto getOrganizations(UUID userId) {
        User user = findById(userId);
        return new GetOrganizationsDto(userOrganizationRepository.findAllByUser(user).stream()
                .map(i -> i.getOrganization()).collect(Collectors.toList()));
    }

    private User findById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new NotFoundException("유저 " + uuid + "를 찾을 수 없습니다."));
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("존재하지 않는 email입니다."));
    }

}
