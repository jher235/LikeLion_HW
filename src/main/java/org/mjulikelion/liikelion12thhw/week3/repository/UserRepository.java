package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.domain.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class UserRepository extends EntityRepository<User> {

    public UserRepository() {
        super(new LinkedList<>(), User.class);
    }


}
