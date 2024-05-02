package org.mjulikelion.liikelion12thhw.week3.dto.request.memo;

import lombok.Getter;
import org.mjulikelion.liikelion12thhw.week3.domain.Memo;

@Getter
public class GetMemoDto {


    private final String title;
    private final int memoId;
    private final String writerId;
    private final String content;

    public GetMemoDto(Memo memo) {
        this.title = memo.getTitle();
        this.memoId = memo.getMemoId();
        this.writerId = memo.getWriterId();
        this.content = memo.getContent();
    }


}
