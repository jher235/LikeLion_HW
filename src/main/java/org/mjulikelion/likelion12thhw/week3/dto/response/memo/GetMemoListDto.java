package org.mjulikelion.likelion12thhw.week3.dto.response.memo;

import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.Memo;

import java.util.List;

@Getter
public class GetMemoListDto {
    private final List<Memo> memoList;

    public GetMemoListDto(List<Memo> memoList) {
        this.memoList = memoList;
    }
}
