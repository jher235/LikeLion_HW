package org.mjulikelion.liikelion12thhw.week3.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.domain.Like;
import org.mjulikelion.liikelion12thhw.week3.dto.request.like.GetLikeListDto;
import org.mjulikelion.liikelion12thhw.week3.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public void register(String userName, int memoId) {
        Like like = new Like(memoId, userName);
        if (likeRepository.isLiked(like)) {  //좋아요가 있는지 확인 후 이미 있을 경우 삭제, 아닐 경우 생성.
            likeRepository.remove(like);
        } else likeRepository.create(like);
    }

    public GetLikeListDto getList(int memoId) {
        GetLikeListDto getLikeListDto = new GetLikeListDto(likeRepository.getList(memoId));
        return getLikeListDto;
    }


}

