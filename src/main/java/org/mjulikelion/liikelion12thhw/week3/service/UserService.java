package org.mjulikelion.liikelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.liikelion12thhw.week3.dto.request.user.UserModifyDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.user.UserRegisterDto;
import org.mjulikelion.liikelion12thhw.week3.exception.ConflictException;
import org.mjulikelion.liikelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.liikelion12thhw.week3.model.User;
import org.mjulikelion.liikelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void registerUser(UserRegisterDto userRegisterDto) {
        UUID userId = UUID.randomUUID();
        User user = User.builder()
                .name(userRegisterDto.getName())
                .build();

        if (userRepository.isExist(userId)) {
            throw new ConflictException(userId + "는 이미 존재하는 아이디입니다.");
        }
        userRepository.save(user);
        log.info("userId: " + userId);
    }

    public void modify(UserModifyDto userModifyDto, UUID userId) {
        User user = this.findById(userId);
        user.setName(userModifyDto.getName());
        userRepository.save(user);
    }

    public void delete(UUID userId) {
        User user = this.findById(userId);
        userRepository.delete(user);
    }

    private User findById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new NotFoundException("유저 " + uuid + "를 찾을 수 없습니다."));
    }

}
