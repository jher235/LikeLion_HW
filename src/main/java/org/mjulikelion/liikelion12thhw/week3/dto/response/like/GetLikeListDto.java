package org.mjulikelion.liikelion12thhw.week3.dto.response.like;

import lombok.Getter;
import org.mjulikelion.liikelion12thhw.week3.domain.Like;

import java.util.List;

@Getter
public class GetLikeListDto {
    private final int count;
    private final List<Like> likeList;


    public GetLikeListDto(int count, List<Like> nameList) {
        this.count = count;
        this.likeList = nameList;
    }
}
