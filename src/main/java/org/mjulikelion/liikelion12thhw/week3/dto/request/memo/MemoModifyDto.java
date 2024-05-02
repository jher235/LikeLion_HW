package org.mjulikelion.liikelion12thhw.week3.dto.request.memo;

import lombok.Getter;

@Getter
public class MemoModifyDto {

    //    @NotNull(message = "content가 null입니다.")
    private String content;

    //    @NotNull(message = "title이 null입니다")
    private String title;
}
