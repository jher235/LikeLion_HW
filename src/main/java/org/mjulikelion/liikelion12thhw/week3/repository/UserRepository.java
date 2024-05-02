package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.domain.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements CustomRepository<User> {

    private final List<User> userList = new LinkedList();

    @Override
    public void create(User user) {
        String userId = user.getUserId();
        userList.add(user);
    }

    public void modify(User preUser, User modUser) {
        userList.remove(preUser);
        userList.add(modUser);
    }

    public void remove(User user) {
        userList.remove(user);
    }

    public boolean isExist(String userId) {
        return userList.stream()
                .anyMatch(user -> user.getUserId().equals(userId));
    }

    public Optional<User> get(String userId) {
        Optional<User> user = userList.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
        return user;
    }

}
