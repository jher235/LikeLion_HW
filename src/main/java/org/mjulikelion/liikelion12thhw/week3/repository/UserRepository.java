package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean isExist(UUID userId);//자동으로 만들어줌
}
