package org.mjulikelion.likelion12thhw.week3.dto.response.memo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.Memo;

@Getter
@Builder
@AllArgsConstructor
public class MemoResponse {

    private String title;
    private String content;

    //생성자를 사용하는 방법
    public MemoResponse(Memo memo) {
        this.title = memo.getTitle();
        this.content = memo.getContent();
    }

    public static MemoResponse memoResponse(Memo memo) {
        return new MemoResponse(memo.getTitle(), memo.getContent());
    }

}
