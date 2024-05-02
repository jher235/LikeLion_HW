package org.mjulikelion.liikelion12thhw.week3.repository;

import org.mjulikelion.liikelion12thhw.week3.domain.Like;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LikeRepository implements CustomRepository<Like> {

    private final List<Like> likeList = new LinkedList<>();

    //좋아요 생성
    @Override
    public void create(Like like) {
        likeList.add(like);
    }

    //좋아요 리스트 반환, 파라미터를 Memo로 두는 게 더 유연해 보이지만, 가져오는 방법이 마땅치 않음.
    public List<Like> getList(int memoId) {
        List<Like> filteredList = likeList.stream().filter(like -> like.getMemoId() == memoId).collect(Collectors.toList());
        return filteredList;
    }

    //좋아요 삭제
    public void remove(Like like) {
        likeList.remove(like);
    }

    public boolean isLiked(Like like) {
        return likeList.stream().anyMatch(l -> l.getUserName().equals(like.getUserName()) && l.getMemoId() == like.getMemoId());
    }
}
