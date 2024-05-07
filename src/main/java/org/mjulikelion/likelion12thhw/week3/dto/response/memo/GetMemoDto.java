package org.mjulikelion.likelion12thhw.week3.dto.response.memo;

import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.Memo;

@Getter
public class GetMemoDto {


    private final String title;
    private final String content;

    public GetMemoDto(Memo memo) {
        this.title = memo.getTitle();
        this.content = memo.getContent();
    }


}
