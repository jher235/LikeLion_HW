package org.mjulikelion.likelion12thhw.week3.dto.response.like;

import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.MemoLike;

import java.util.List;

@Getter
public class GetLikeListDto {
    private final int count;
    private final List<MemoLike> likeList;


    public GetLikeListDto(int count, List<MemoLike> nameList) {
        this.count = count;
        this.likeList = nameList;
    }
}
