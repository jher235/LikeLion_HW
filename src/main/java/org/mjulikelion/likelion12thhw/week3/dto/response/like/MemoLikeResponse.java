package org.mjulikelion.likelion12thhw.week3.dto.response.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.model.MemoLike;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
public class MemoLikeResponse {
    private String name;

    public static MemoLikeResponse memoLikeResponse(MemoLike memoLike) {
        log.info(memoLike.getUser().getName());
        return new MemoLikeResponse(memoLike.getUser().getName());
    }
}
