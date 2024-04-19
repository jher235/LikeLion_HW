package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> userList = new LinkedList();

    public void create(User user){
        Optional<User> existUser =  userList.stream().filter(i->i.getUid().equals(user.getUid())).findAny();
        if(existUser.orElse(null)!=null) throw new IllegalArgumentException(user.getUid()+"는 이미 존재하는 아이디입니다.");
        userList.add(user);
    }





}
