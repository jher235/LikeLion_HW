package org.mjulikelion.liikelion12thhw.week3.dto.request.memo;

import org.mjulikelion.liikelion12thhw.week3.Memo;

import java.util.LinkedList;
import java.util.List;

public class GetMemoListDto {
    List<Memo> memoList = new LinkedList<>();

    public GetMemoListDto(List<Memo> memoList) {
        this.memoList = memoList;
    }
}
