package org.mjulikelion.liikelion12thhw.week3.dto.request.memo;

import lombok.Getter;
import org.mjulikelion.liikelion12thhw.week3.domain.Memo;

import java.util.LinkedList;
import java.util.List;

@Getter
public class GetMemoListDto {
    private List<Memo> memoList = new LinkedList<>();

    public GetMemoListDto(List<Memo> memoList) {
        this.memoList = memoList;
    }
}
