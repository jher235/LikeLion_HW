package org.mjulikelion.likelion12thhw.week3.repository;

import org.mjulikelion.likelion12thhw.week3.model.Memo;
import org.mjulikelion.likelion12thhw.week3.model.MemoLike;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Repository
public interface MemoLikeRepository extends JpaRepository<MemoLike, UUID> {

    List<MemoLike> findAllByMemo(Memo memo);

//    Boolean existsBy(User user, Memo memo);

    Optional<MemoLike> findMemoLikeByUserAndMemo(User user, Memo memo);


}
