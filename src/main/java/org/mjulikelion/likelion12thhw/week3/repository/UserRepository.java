package org.mjulikelion.likelion12thhw.week3.repository;

import org.mjulikelion.likelion12thhw.week3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

//@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);//email로 유저 반환

    boolean existsByEmail(String email);//email 중복확인


}
