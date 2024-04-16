package org.mjulikelion.liikelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.User;
import org.mjulikelion.liikelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void registerUser(User user){
        userRepository.create(user);
    }
}
