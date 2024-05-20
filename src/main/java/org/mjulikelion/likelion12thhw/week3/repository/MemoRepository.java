package org.mjulikelion.likelion12thhw.week3.repository;

import org.mjulikelion.likelion12thhw.week3.model.Memo;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

//@Repository
public interface MemoRepository extends JpaRepository<Memo, UUID> {
    List<Memo> findAllByUser(User user);

}
