package org.mjulikelion.liikelion12thhw.week3.dto.request.like;

import lombok.Getter;
import org.mjulikelion.liikelion12thhw.week3.domain.Like;

import java.util.LinkedList;
import java.util.List;

@Getter
public class GetLikeListDto {
    private List<Like> likeList = new LinkedList<>();

    public GetLikeListDto(List<Like> likeList) {
        this.likeList = likeList;
    }
}
