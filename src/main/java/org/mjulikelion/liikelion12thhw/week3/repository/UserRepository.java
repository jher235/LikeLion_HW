package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.User;
import org.mjulikelion.liikelion12thhw.week3.repository.CustomRepository;
import org.springframework.stereotype.Repository;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements CustomRepository<User> {

    private final List<User> userList = new LinkedList();

    @Override
    public void create(User user){
        String userId = user.getUserId();

        if(isExist(userId)) throw new IllegalArgumentException(user.getUserId()+"는 이미 존재하는 아이디입니다.");

        userList.add(user);
    }

    private boolean isExist(String userId){
        return userList.stream()
                .anyMatch(user -> user.getUserId().equals(userId));
    }
}
