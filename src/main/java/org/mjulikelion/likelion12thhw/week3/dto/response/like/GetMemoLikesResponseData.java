package org.mjulikelion.likelion12thhw.week3.dto.response.like;

import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.MemoLike;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetMemoLikesResponseData {
    private final int count;
    private final List<MemoLikeResponse> likeList;

    public GetMemoLikesResponseData(int count, List<MemoLike> likeList) {
        this.count = count;
        this.likeList = likeList.stream().map(i -> new MemoLikeResponse(i.getUser().getName())).collect(Collectors.toList());
//        this.likeList = likeList.stream().map(i -> MemoLikeResponse.memoLikeResponse(i)).collect(Collectors.toList());
    }
}
