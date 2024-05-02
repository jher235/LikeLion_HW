package org.mjulikelion.liikelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.domain.User;
import org.mjulikelion.liikelion12thhw.week3.dto.request.user.UserModifyDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.user.UserRegisterDto;
import org.mjulikelion.liikelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void registerUser(UserRegisterDto userRegisterDto) {
        if (userRepository.isExist(userRegisterDto.getUserId())) {
            throw new IllegalArgumentException(userRegisterDto.getUserId() + "는 이미 존재하는 아이디입니다.");
        }
        User user = new User(userRegisterDto.getUserId(), userRegisterDto.getName());
        userRepository.create(user);
    }

    public void modify(UserModifyDto userModifyDto, String userId) {
        User preUser = userRepository.get(userId)
                .orElseThrow(() -> new IllegalArgumentException("userId: " + userId + "는 존재하지 않습니다."));
        User modUser = new User(userId, userModifyDto.getName());
        userRepository.modify(preUser, modUser);
    }

    public void delete(String userId) {
        User user = userRepository.get(userId)
                .orElseThrow(() -> new IllegalArgumentException("userId: " + userId + "는 존재하지 않습니다."));
        userRepository.remove(user);
    }

}
