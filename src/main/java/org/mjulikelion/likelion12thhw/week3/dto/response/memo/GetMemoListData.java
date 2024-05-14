package org.mjulikelion.likelion12thhw.week3.dto.response.memo;

import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.Memo;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetMemoListData {
    private final List<MemoResponse> memoList;

    public GetMemoListData(List<Memo> memoList) {
//        this.memoList = memoList.stream().map(i -> new MemoResponse(i)).collect(Collectors.toList());
        this.memoList = memoList.stream().map(i -> MemoResponse.memoResponse(i)).collect(Collectors.toList());
    }
}
