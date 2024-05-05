package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.model.Memo;
import org.mjulikelion.liikelion12thhw.week3.model.MemoLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

//@Repository
public interface MemoLikeRepository extends JpaRepository<MemoLike, UUID> {

    List<MemoLike> findAllByMemo(Memo memo);


}
